package com.Grupo15.BolsaDeTrabajo.Features.Projects;

import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectUpdateRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    public ResponseEntity<ProjectResponseDTO> create(@RequestBody ProjectRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(requestDTO));
    }

    @PutMapping("/{projectId}/update")
    public ResponseEntity<ProjectResponseDTO> update(
            @PathVariable UUID projectId,
            @RequestBody ProjectUpdateRequestDTO requestDTO) {
        return ResponseEntity.ok(projectService.update(projectId, requestDTO));
    }

    @DeleteMapping("/{projectId}/delete")
    public ResponseEntity<Void> delete(@PathVariable UUID projectId) {
        projectService.delete(projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/candidate/{candidateId}")
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects(@PathVariable UUID candidateId) {
        return ResponseEntity.ok(projectService.getAllProjects(candidateId));
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<ProjectResponseDTO> getProject(@PathVariable UUID projectId) {
        return ResponseEntity.ok(projectService.getProject(projectId));
    }
}
