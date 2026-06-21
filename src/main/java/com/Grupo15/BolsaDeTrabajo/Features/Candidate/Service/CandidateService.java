package com.Grupo15.BolsaDeTrabajo.Features.Candidate.Service;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Mapper.CandidateMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.*;
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
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class CandidateService {

    private final CandidateRepository candidateRepository;
    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepositorySecurity;

    @Transactional
    public CandidatesResponseDTO creteCandidate(CandidatesRequestDTO candidatesRequestDTO) {
        if (!candidatesRequestDTO.email().contains("@")) {
            throw new BussinesRulesException("The email format is invalid.");
        }

        if (candidateRepository.existsByEmail(candidatesRequestDTO.email())) {
            throw new ExistingEmailException("The email " + candidatesRequestDTO.email() + " already exists in the system.");
        }

        if (credentialsRepository.existsByUsername(
                candidatesRequestDTO.username())) {

            throw new RuntimeException(
                    "El username ya existe");
        }


        if (candidatesRequestDTO.password() == null || candidatesRequestDTO.password().length() < 8) {
            throw new InvalidPasswordException("The password must be at least 8 characters long.");
        }

        if (candidatesRequestDTO.name() == null || candidatesRequestDTO.name().isBlank()) {
            throw new RuntimeException("The name is required.");
        }


        CandidatesEntity candidate = new CandidatesEntity();

        candidate.setName(candidatesRequestDTO.name());
        candidate.setEmail(candidatesRequestDTO.email());
        candidate.setActive(true);
        candidate.setLastName(candidatesRequestDTO.lastName());

        candidate.setProfessionalTitle(candidatesRequestDTO.professionalTitle());
        candidate.setSummary(candidatesRequestDTO.summary());
        candidate.setUpdatedAt(Timestamp.from(Instant.now()));
        candidate.setCvUrl(candidatesRequestDTO.cvUrl());
        candidate.setLinkedinUrl(candidatesRequestDTO.linkedinUrl());
        candidate.setPhotoUrl(candidatesRequestDTO.photoUrl());
        //candidate.setProjects(new ArrayList<>());
        //candidate.setApplications(new ArrayList<>());
        //candidate.setAbilityCandidates(new ArrayList<>());
        //candidate.setLaboralExperiences(new ArrayList<>());
        //candidate.setSaved(new ArrayList<>());

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
    public void deleteCandidate(UUID id)
    {
        CandidatesEntity candidate = candidateRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Could not deactivate the candidate. No candidate found with ID "));

        if (!candidate.isActive()) {

            throw new InactiveUserException("This candidate's profile is no longer available (deactivated).");

        }

        candidate.setActive(false);
        //candidate.setUpdatedAt(Timestamp.from(Instant.now()));

        candidateRepository.save(candidate);

    }

    //Obtener candidato en base al id
    @Transactional
    public CandidatesResponseDTO getCandidate(UUID id)
    {
        CandidatesEntity candidate = candidateRepository.findById(id)

                .orElseThrow(()-> new ElementNotFoundException("Candidate not found."));

        if(!candidate.isActive()) {

            throw new InactiveUserException("Profile not available.");

        }
        return CandidateMapper.toDto(candidate);
    }

    //Actualizar candidato en base al id
    @Transactional
    public CandidatesResponseDTO updateCandidate(UUID id, CandidatesRequestDTO requestDTO){

        CandidatesEntity candidatesEntity = candidateRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Candidate not found."));

        //Perfil no activo
        if (!candidatesEntity.isActive()) {

            throw new ElementNotFoundException("Profile not found.");
        }

        //Mail ya existe (en base al que se esta ingresando)
        if (!candidatesEntity.getEmail().equalsIgnoreCase(requestDTO.email()) && candidateRepository.existsByEmail(requestDTO.email())) {

            throw new ExistingEmailException("The entered email address is already being used by another user.");
        }

        // Contraseña existente y si cumple con la condicion de ser minimo de 8 caracteres (Luego cambiar para q sea con seguridad alto)
        if (requestDTO.password() != null && !requestDTO.password().isBlank()) {
            if (requestDTO.password().length() < 8) {

                throw new InvalidPasswordException("The new password must be at least 8 characters long.");
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