package com.Grupo15.BolsaDeTrabajo.Features.Candidate.Service;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Mapper.CandidateMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.InvalidPasswordException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ExistingEmailException;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

@Service
@RequiredArgsConstructor
@Transactional
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final RolesRepository rolesRepository;

    @Transactional
    public CandidatesResponseDTO creteCandidate(CandidatesRequestDTO candidatesRequestDTO) {
        if (!candidatesRequestDTO.email().contains("@")) {
            throw new RuntimeException("El formato del correo electrónico es inválido.");
        }

        if (candidateRepository.existsByEmail(candidatesRequestDTO.email())) {
            throw new RuntimeException("EL mail " + candidatesRequestDTO.email() + " ya existe en el sistema");
        }

        if (candidatesRequestDTO.password() == null || candidatesRequestDTO.password().length() < 8) {
            throw new InvalidPasswordException("La contraseña debe tener al menos 8 caracteres");
        }

        if (candidatesRequestDTO.name() == null || candidatesRequestDTO.name().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio.");
        }

        RolesEntity candidateRole = rolesRepository.findByNombre("CANDIDATO")

                .orElseThrow(() -> new RuntimeException("Error del sistema: El rol CANDIDATO no existe configurado en la base de datos."));

        CandidatesEntity candidateEntity = new CandidatesEntity();

        CandidatesEntity candidate = new CandidatesEntity();

        candidate.setName(candidatesRequestDTO.name());
        candidate.setEmail(candidatesRequestDTO.email());
        candidate.setPassword(candidatesRequestDTO.password());
        candidate.setActive(true);
        candidate.setRol(candidateRole);

        //candidate.setLastName(candidatesRequestDTO.lastName());

        candidate.setProfessionalTitle(candidatesRequestDTO.professionalTitle());
        candidate.setSummary(candidatesRequestDTO.summary());
        candidate.setUpdatedAt(Timestamp.from(Instant.now()));
        candidate.setApplications(new ArrayList<>());
        candidate.setAbilityCandidates(new ArrayList<>());
        candidate.setLaboralExperiences(new ArrayList<>());

        candidate.setSaved(new ArrayList<>());

        CandidatesEntity savedCandidate = candidateRepository.save(candidate);

        return CandidateMapper.toDto(savedCandidate);

    }

    //El metodo no borra al usuario de la base de datos sino que actualiza su estado
    @Transactional
    public void deleteCandidate(Long id)
    {
        CandidatesEntity candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No se pudo dar de baja, candidato no encontrado con ID"));

        if (!candidate.isActive()) {

            throw new RuntimeException("El perfil de este candidato ya no está disponible (Dado de baja).");

        }

        candidate.setActive(false);
        //candidate.setUpdatedAt(Timestamp.from(Instant.now()));

        candidateRepository.save(candidate);

    }

    //Obtener candidato en base al id
    @Transactional
    public CandidatesResponseDTO getCandidate(Long id)
    {
        CandidatesEntity candidate = candidateRepository.findById(id)

                .orElseThrow(()-> new RuntimeException("Candidato no encontrado"));

        if(!candidate.isActive()) {

            throw new RuntimeException("Perfil no disponible");

        }
        return CandidateMapper.toDto(candidate);
    }

    //Actualizar candidato en base al id
    @Transactional
    public CandidatesResponseDTO updateCandidate(Long id, CandidatesRequestDTO requestDTO){

        CandidatesEntity candidatesEntity = candidateRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidato no encontrado"));

        //Perfil no activo
        if (!candidatesEntity.isActive()) {

            throw new RuntimeException("Perfil no encontrado");
        }

        //Mail ya existe (en base al que se esta ingresando)
        if (!candidatesEntity.getEmail().equalsIgnoreCase(requestDTO.email()) && candidateRepository.existsByEmail(requestDTO.email())) {

            throw new ExistingEmailException("El mail ingresado ya está siendo utilizado por otro usuario.");
        }

        // Contraseña existente y si cumple con la condicion de ser minimo de 8 caracteres (Luego cambiar para q sea con seguridad alto)
        if (requestDTO.password() != null && !requestDTO.password().isBlank()) {
            if (requestDTO.password().length() < 8) {

                throw new InvalidPasswordException("La nueva contraseña debe tener al menos 8 caracteres.");
            }
        }

        candidatesEntity.setName(requestDTO.name());
        candidatesEntity.setEmail(requestDTO.email());

        //candidatesEntity.setLastName(requestDTO.lastName());
        candidatesEntity.setProfessionalTitle(requestDTO.professionalTitle());
        candidatesEntity.setSummary(requestDTO.summary());
        candidatesEntity.setCvUrl(requestDTO.cvUrl());
        candidatesEntity.setLinkedinUrl(requestDTO.linkedinUrl());
        candidatesEntity.setPhotoUrl(requestDTO.photoUrl());
        candidatesEntity.setUpdatedAt(Timestamp.from(Instant.now()));

        CandidatesEntity updatedCandidate = candidateRepository.save(candidatesEntity);
        return CandidateMapper.toDto(updatedCandidate);
    }


} 