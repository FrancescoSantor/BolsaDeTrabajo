package com.Grupo15.BolsaDeTrabajo.Features.Projects;

import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectUpdateRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Projects", description = "Endpoints for managing candidate academic or professional projects and portfolio links")
public class ProjectController {
    private final ProjectService projectService;

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Create a new project entry", description = "Allows an authenticated candidate to append a professional or academic project to their resume profile.")
    @ApiResponse(responseCode = "201", description = "Project log entry created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request attributes or payload constraints broken")
    @ApiResponse(responseCode = "403", description = "Access denied. Action strictly limited to Candidate permissions context")
    @ApiResponse(responseCode = "404", description = "Target candidate profile context record not found")
    public ResponseEntity<ProjectResponseDTO> create(@Valid @RequestBody ProjectRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(projectService.create(requestDTO));
    }

    @PutMapping("/{projectId}/update")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Update an existing project details", description = "Modifies text fields, links, or chronological scopes of a specific project entry matching the given unique token.")
    @ApiResponse(responseCode = "200", description = "Project details updated successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Action restricted to Candidate identity authority context")
    @ApiResponse(responseCode = "404", description = "Target project data record not found with the provided identifier")
    public ResponseEntity<ProjectResponseDTO> update(
            @Parameter(description = "Secure public UUID of the project entry to be modified") @PathVariable UUID projectId,
            @RequestBody ProjectUpdateRequestDTO requestDTO) {
        return ResponseEntity.ok(projectService.update(projectId, requestDTO));
    }

    @DeleteMapping("/{projectId}/delete")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Remove a project log", description = "Deletes a specific project entry from the candidate's professional records space.")
    @ApiResponse(responseCode = "204", description = "Project record dropped from system logs successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Action restricted to Candidate identity authority context")
    @ApiResponse(responseCode = "404", description = "Target project data record not found with the provided identifier")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Secure public UUID of the project entry to delete") @PathVariable UUID projectId) {
        projectService.delete(projectId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/candidate/{candidateId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
    @Operation(summary = "Fetch all projects by candidate ID", description = "Retrieves the comprehensive portfolio and showcase projects linked to a specific candidate profile.")
    @ApiResponse(responseCode = "200", description = "Candidate projects ledger collection retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Target candidate identity data context not found")
    public ResponseEntity<List<ProjectResponseDTO>> getAllProjects(
            @Parameter(description = "Secure public UUID filter of the target candidate profile") @PathVariable UUID candidateId) {
        return ResponseEntity.ok(projectService.getAllProjects(candidateId));
    }

    @GetMapping("/{projectId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
    @Operation(summary = "Retrieve specific project information", description = "Fetches complete detail records and metadata context for a unique safe project identity.")
    @ApiResponse(responseCode = "200", description = "Project details model schema payload retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Target project data record not found with the provided identifier")
    public ResponseEntity<ProjectResponseDTO> getProject(
            @Parameter(description = "Secure public UUID of the targeted project log") @PathVariable UUID projectId) {
        return ResponseEntity.ok(projectService.getProject(projectId));
    }
}