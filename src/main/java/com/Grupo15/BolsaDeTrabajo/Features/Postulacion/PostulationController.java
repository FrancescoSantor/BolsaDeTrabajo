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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
    @Operation(summary = "Submit a new job application", description = "Allows an authenticated candidate to apply for an active job offer by providing a cover letter and linking their profile metrics.")
    @ApiResponse(responseCode = "201", description = "Application submitted successfully")
    @ApiResponse(responseCode = "400", description = "Duplicate application detected or target job listing is already closed")
    @ApiResponse(responseCode = "403", description = "Access denied. Restricted to Candidate accounts context")
    @ApiResponse(responseCode = "404", description = "Target job offer or candidate entity context not found")
    public ResponseEntity<PostulationResponseDTO> create(
            @Valid @RequestBody PostulationNewDTO newDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postulationService.CreatePostulation(newDTO, userDetails.getUsername()));
    }

    @Valid
    @PatchMapping("/{externalId}/status")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Update postulation workflow state", description = "Allows a company owner to move a candidate's application through evaluation stages (e.g., PASSED, REJECTED).")
    @ApiResponse(responseCode = "200", description = "Postulation status workflow modified successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Action restricted to Company role context authority")
    @ApiResponse(responseCode = "404", description = "Postulation log record not found with the provided identifier")
    public ResponseEntity<PostulationResponseDTO> updateStatus(
            @Parameter(description = "Secure public UUID of the job application entry") @PathVariable UUID externalId,
            @Parameter(description = "New target workflow state enum parameter") @RequestParam PostulationState state,
            @AuthenticationPrincipal UserDetails userDetails) {

        return ResponseEntity.status(HttpStatus.OK)
                .body(postulationService.updateStatusPostulation(externalId, state, userDetails.getUsername()));
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    @Operation(summary = "Query postulations with optional filters", description = "Retrieves a tailored list of applications matching optional criteria such as target candidate, specific job offer, or current status state.")
    @ApiResponse(responseCode = "200", description = "Filtered applications logs collection retrieved successfully")
    public ResponseEntity<List<PostulationResponseDTO>> getWithFilters(
            @Parameter(description = "Optional secure UUID filter for candidate tracking context") @RequestParam(required = false) UUID candidateId,
            @Parameter(description = "Optional secure UUID filter for job offer publication link") @RequestParam(required = false) UUID offerId,
            @Parameter(description = "Optional status state enum criteria to filter results query") @RequestParam(required = false) PostulationState state) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(postulationService.findBy(candidateId, offerId, state));
    }

    @DeleteMapping("/{externalId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Cancel / Delete a job application", description = "Performs a safe logical delete status cycle. This action is restricted exclusively to applications that are still in WAITING status.")
    @ApiResponse(responseCode = "204", description = "Job application canceled and marked as inactive successfully")
    @ApiResponse(responseCode = "400", description = "Application has already progressed past the WAITING status state or is already inactive")
    @ApiResponse(responseCode = "403", description = "Access denied. Ownership validation failed")
    @ApiResponse(responseCode = "404", description = "Postulation entity context data records not found")
    public ResponseEntity<Void> delete(
            @Parameter(description = "Secure public UUID of the postulation to delete") @PathVariable UUID externalId,
            @AuthenticationPrincipal UserDetails userDetails) {
        postulationService.Delete(externalId, userDetails.getUsername());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Retorna 204 No Content (estándar para DELETE exitosos)
    }
}