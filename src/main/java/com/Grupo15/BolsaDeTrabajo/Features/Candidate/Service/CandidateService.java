package com.Grupo15.BolsaDeTrabajo.Features.Candidate.Service;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Mapper.CandidateMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exception.ContraseniaInvalidaException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exception.MailExistenteException;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.Roles;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesRepository;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsRepository;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.Role;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.RoleEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final RolesRepository rolesRepository;
    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepositorySecurity;

    @Transactional
    public CandidatesResponseDTO creteCandidate(CandidatesRequestDTO candidatesRequestDTO) {
        if (!candidatesRequestDTO.email().contains("@")) {
            throw new RuntimeException("El formato del correo electrónico es inválido.");
        }

        if (candidateRepository.existsByEmail(candidatesRequestDTO.email())) {
            throw new RuntimeException("EL mail " + candidatesRequestDTO.email() + " ya existe en el sistema");
        }

        if (credentialsRepository.existsByUsername(
                candidatesRequestDTO.username())) {

            throw new RuntimeException(
                    "El username ya existe");
        }

        if (candidatesRequestDTO.password() == null || candidatesRequestDTO.password().length() < 8) {
            throw new ContraseniaInvalidaException("La contraseña debe tener al menos 8 caracteres");
        }

        if (candidatesRequestDTO.name() == null || candidatesRequestDTO.name().isBlank()) {
            throw new RuntimeException("El nombre es obligatorio.");
        }

        RolesEntity candidateRole = rolesRepository.findByRol(Roles.CANDIDATE) // aca habria que usar findByRol que usa luca. y boorar findByNombre del repo.

                .orElseThrow(() -> new RuntimeException("Error del sistema: El rol CANDIDATO no existe configurado en la base de datos."));

        CandidatesEntity candidate = new CandidatesEntity();

        candidate.setName(candidatesRequestDTO.name());
        candidate.setEmail(candidatesRequestDTO.email());
      //  candidate.setPassword(candidatesRequestDTO.password());
        candidate.setActive(true);
        candidate.setRol(candidateRole);

        candidate.setLastName(candidatesRequestDTO.lastName());
        candidate.setProfessionalTitle(candidatesRequestDTO.professionalTitle());
        candidate.setSummary(candidatesRequestDTO.summary());
        candidate.setUpdatedAt(Timestamp.from(Instant.now()));
        candidate.setApplications(new ArrayList<>());
        candidate.setAbilityCandidates(new ArrayList<>());
        candidate.setLaboralExperiences(new ArrayList<>());

        candidate.setSaved(new ArrayList<>());

        CandidatesEntity savedCandidate = candidateRepository.save(candidate);

        RoleEntity securityRole = roleRepositorySecurity
                .findByRole(Role.ROLE_CANDIDATE)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        CredentialsEntity credentials =
                CredentialsEntity.builder()
                        .username(candidatesRequestDTO.username())
                        .password(
                                passwordEncoder.encode(
                                        candidatesRequestDTO.password()
                                )
                        )
                        .enabled(true)
                        .usuario(savedCandidate)
                        .roles(Set.of(securityRole))
                        .build();

        credentialsRepository.save(credentials);

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

            throw new MailExistenteException("El mail ingresado ya está siendo utilizado por otro usuario.");
        }

        // Contraseña existente y si cumple con la condicion de ser minimo de 8 caracteres (Luego cambiar para q sea con seguridad alto)
        if (requestDTO.password() != null && !requestDTO.password().isBlank()) {
            if (requestDTO.password().length() < 8) {

                throw new ContraseniaInvalidaException("La nueva contraseña debe tener al menos 8 caracteres.");
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