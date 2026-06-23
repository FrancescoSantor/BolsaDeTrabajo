package com.Grupo15.BolsaDeTrabajo.Features.Projects;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectUpdateRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
@RequiredArgsConstructor
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;
    private final CredentialsRepository credentialsRepository;

    private final CandidateRepository candidateRepository;

    public ProjectResponseDTO create(ProjectRequestDTO requestDTO, Authentication authentication) {
        CandidatesEntity candidate = candidateRepository.findByExternalId(requestDTO.candidateId())
                .orElseThrow(() -> new ElementNotFoundException("The candidate has not been found."));

        CredentialsEntity credentials = credentialsRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("authenticated user not found"));

        UsersEntity loggedUser = credentials.getUsuario();

        if (!loggedUser.getId().equals(candidate.getId())){
            throw new RuntimeException("you don't have permissions to create a project for this user ");
        }

        if (requestDTO.initialDate().after(requestDTO.endDate())) {
            throw new RuntimeException("The initial date cannot be earlier than the end date.");
        }

        ProjectEntity project = projectMapper.toEntity(requestDTO);
        project.setCandidate(candidate);
        project.setProjectName(requestDTO.projectName());
        project.setDescription(requestDTO.description());
        project.setInitialDate(requestDTO.initialDate());
        project.setEndDate(requestDTO.endDate());
        project.setUrlLink(requestDTO.urlLink());

        return projectMapper.toDto(projectRepository.save(project));
    }

    public ProjectResponseDTO update(UUID projectId, ProjectUpdateRequestDTO updateRequestDTO, Authentication authentication) {
        ProjectEntity project = projectRepository.findByExternalId(projectId)
                .orElseThrow(() -> new ElementNotFoundException("The Project has not been found."));

        CredentialsEntity credentials = credentialsRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("authenticated user not found"));

        UsersEntity loggedUser = credentials.getUsuario();

        if (!loggedUser.getId().equals(project.getCandidate().getId())){
            throw new RuntimeException("you don't have permissions to create a project for this user ");
        }


        if(!project.getCandidate().isActive()) {
            throw new RuntimeException("The project cannot be updated because the candidate is not active.");
        }

        if(updateRequestDTO.projectName() != null && !updateRequestDTO.projectName().isBlank()) {
            project.setProjectName(updateRequestDTO.projectName());
        }
        if(updateRequestDTO.description() != null && !updateRequestDTO.description().isBlank()) {
            project.setDescription(updateRequestDTO.description());
        }
        if(updateRequestDTO.initialDate() != null) {
            project.setInitialDate(updateRequestDTO.initialDate());
        }
        if(updateRequestDTO.endDate() != null) {
            project.setEndDate(updateRequestDTO.endDate());
        }
        if(updateRequestDTO.urlLink() != null && !updateRequestDTO.urlLink().isBlank()) {
            project.setUrlLink(updateRequestDTO.urlLink());
        }

        return projectMapper.toDto(projectRepository.save(project));
    }

    public void delete(UUID projectId, Authentication authentication) {
        ProjectEntity project = projectRepository.findByExternalId(projectId)
                .orElseThrow(() -> new ElementNotFoundException("The Project has not been found."));

        CredentialsEntity credentials = credentialsRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("authenticated user not found"));

        UsersEntity loggedUser = credentials.getUsuario();

        if (!loggedUser.getId().equals(project.getCandidate().getId())){
            throw new RuntimeException("you don't have permissions to create a project for this user ");
        }

        if(!project.getCandidate().isActive()) {
            throw new RuntimeException("The project cannot be deleted because the candidate is not active.");
        }

        projectRepository.delete(project);
    }

    public List<ProjectResponseDTO> getAllProjects(UUID candidateId, Authentication authentication) {
        CandidatesEntity candidate = candidateRepository.findByExternalId(candidateId)
                .orElseThrow(() -> new ElementNotFoundException("The candidate has not been found."));

        CredentialsEntity credentials = credentialsRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("authenticated user not found"));

        UsersEntity loggedUser = credentials.getUsuario();

        if (!loggedUser.getId().equals(candidate.getId())){
            throw new RuntimeException("you don't have permissions to create a project for this user ");
        }

        return projectRepository.findByCandidate(candidate)
                .stream()
                .map(projectMapper::toDto)
                .toList();
    }

    public ProjectResponseDTO getProject(UUID projectId, Authentication authentication) {
        ProjectEntity project = projectRepository.findByExternalId(projectId)
                .orElseThrow(() -> new ElementNotFoundException("The Project has not been found."));

        CredentialsEntity credentials = credentialsRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("authenticated user not found"));

        UsersEntity loggedUser = credentials.getUsuario();

        if (!loggedUser.getId().equals(project.getCandidate().getId())){
            throw new RuntimeException("you don't have permissions to create a project for this user ");
        }

        return projectMapper.toDto(project);
    }
}
