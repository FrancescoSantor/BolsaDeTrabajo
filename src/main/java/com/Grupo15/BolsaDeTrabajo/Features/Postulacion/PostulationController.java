package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO.PostulationNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO.PostulationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication; // <-- IMPORTANTE: Usamos esta clase directamente
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/BolsaDeTrabajo/postulation")
@RequiredArgsConstructor
@Tag(name = "Postulations", description = "Endpoints for managing job applications, status workflows, and candidate submission tracking")
public class PostulationController {

    private final PostulationService postulationService;

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Submit a new job application")
    @ApiResponse(responseCode = "201", description = "Application submitted successfully")
    public ResponseEntity<PostulationResponseDTO> create(
            @Valid @RequestBody PostulationNewDTO newDTO,
            Authentication authentication) { // <-- Cambiado de UserDetails a Authentication

        // Extraemos el username de forma segura
        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postulationService.CreatePostulation(newDTO, username));
    }

    @Valid
    @PatchMapping("/{externalId}/status")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Update postulation workflow state")
    @ApiResponse(responseCode = "200", description = "Postulation status workflow modified successfully")
    public ResponseEntity<PostulationResponseDTO> updateStatus(
            @Parameter(description = "Secure public UUID of the job application entry") @PathVariable UUID externalId,
            @Parameter(description = "New target workflow state enum parameter") @RequestParam PostulationState state,
            Authentication authentication) { // <-- Cambiado de UserDetails a Authentication

        String username = authentication.getName();
        return ResponseEntity.status(HttpStatus.OK)
                .body(postulationService.updateStatusPostulation(externalId, state, username));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    @Operation(summary = "Query postulations with optional filters")
    public ResponseEntity<List<PostulationResponseDTO>> getWithFilters(
            @RequestParam(required = false) UUID candidateId,
            @RequestParam(required = false) UUID offerId,
            @RequestParam(required = false) PostulationState state) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postulationService.findBy(candidateId, offerId, state));
    }

    @DeleteMapping("/{externalId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Cancel / Delete a job application")
    @ApiResponse(responseCode = "204", description = "Job application canceled successfully")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Secure public UUID of the postulation to delete") @PathVariable UUID externalId,
            Authentication authentication) { // <-- Cambiado de UserDetails a Authentication

        String username = authentication.getName();
        postulationService.Delete(externalId, username);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/candidate/{candidateId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Get candidate postulations history", description = "Retrieves a complete list of all job applications submitted by the designated candidate.")
    @ApiResponse(responseCode = "200", description = "Candidate postulations history fetched successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Candidates can only fetch their own application history")
    @ApiResponse(responseCode = "404", description = "Candidate profile context not found")
    public ResponseEntity<List<PostulationResponseDTO>> getPostulationsByCandidate(
            @Parameter(description = "Unique external UUID of the target candidate") @PathVariable UUID candidateId,
            Authentication authentication) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postulationService.getPostulationsByCandidate(candidateId, authentication.getName()));
    }
}