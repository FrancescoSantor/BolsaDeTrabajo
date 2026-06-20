package com.Grupo15.BolsaDeTrabajo.Features.Projects;

import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectUpdateRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/BolsaDeTrabajo/projects")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ProjectResponseDTO> create(@Valid @RequestBody ProjectRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(requestDTO));
    }

    @PutMapping("/{projectId}/update")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<ProjectResponseDTO> update(
            @PathVariable UUID projectId,
            @RequestBody ProjectUpdateRequestDTO requestDTO) {
        return ResponseEntity.ok(projectService.update(projectId, requestDTO));
    }

    @DeleteMapping("/{projectId}/delete")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Void> delete(@PathVariable UUID projectId) {
        projectService.delete(projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/candidate/{candidateId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects(@PathVariable UUID candidateId) {
        return ResponseEntity.ok(projectService.getAllProjects(candidateId));
    }

    @GetMapping("/{projectId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
    public ResponseEntity<ProjectResponseDTO> getProject(@PathVariable UUID projectId) {
        return ResponseEntity.ok(projectService.getProject(projectId));
    }
}
