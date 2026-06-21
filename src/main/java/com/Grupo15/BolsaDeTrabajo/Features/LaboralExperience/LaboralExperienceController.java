package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience;


import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceResponseDTO;
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
@RequestMapping("/BolsaDeTrabajo/laboralExperiences")
@RequiredArgsConstructor
@Tag(name = "Laboral Experiences", description = "Endpoints for managing and retrieving candidate professional backgrounds and work history")
public class LaboralExperienceController {

    private final LaboralExperienceService laboralExperienceService;

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Create a new laboral experience", description = "Allows an authenticated candidate to add a professional background record to their profile.")
    @ApiResponse(responseCode = "201", description = "Laboral experience record created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request payload or end date is prior to initial date")
    @ApiResponse(responseCode = "403", description = "Access denied. Candidate role required")
    @ApiResponse(responseCode = "404", description = "Associated candidate profile not found")
    public ResponseEntity<LaboralExperienceResponseDTO> createExperience(@Valid @RequestBody LaboralExperienceRequestDTO requestDto) {
        LaboralExperienceResponseDTO response = laboralExperienceService.createExperience(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // Devuelve estado 201 CREATED
    }

    @PutMapping("/{externalId}")
    @PreAuthorize("hasRole('CANDIDATE')")// Captura el UUID de la URL   // Valida los datos editados
    @Operation(summary = "Update an existing laboral experience", description = "Modifies the details of a specific professional history record using its secure external UUID.")
    @ApiResponse(responseCode = "200", description = "Laboral experience record updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid validation data or inconsistent date range provided")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Laboral experience record not found with the provided identifier")
    public ResponseEntity<LaboralExperienceResponseDTO> updateExperience(
            @Parameter(description = "Secure public UUID of the laboral experience to update")@PathVariable UUID externalId, @Valid @RequestBody LaboralExperienceRequestDTO requestDto) {
        LaboralExperienceResponseDTO response = laboralExperienceService.updateExperience(externalId, requestDto);
        return ResponseEntity.ok(response); // Devuelve estado 200 OK con los cambios
    }


    @DeleteMapping("/{externalId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Delete a laboral experience record", description = "Permanently removes a specific professional history entry from the database using its external UUID.")
    @ApiResponse(responseCode = "204", description = "Laboral experience deleted successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Laboral experience record not found")
    public ResponseEntity<Void> deleteExperience(
            @Parameter(description = "Secure public UUID of the laboral experience to delete") @PathVariable UUID externalId) {
        laboralExperienceService.deleteExperience(externalId);
        return ResponseEntity.noContent().build(); // Devuelve estado 204 NO CONTENT
    }


    @GetMapping("/{externalId}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    @Operation(summary = "Get laboral experience by external ID", description = "Fetches the full detail logs of a specific work background entry via its unique public UUID.")
    @ApiResponse(responseCode = "200", description = "Laboral experience details retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Laboral experience entity not found")
    public ResponseEntity<LaboralExperienceResponseDTO> getExperienceByExternalId(
            @Parameter(description = "Secure public UUID of the targeted work experience entry") @PathVariable UUID externalId) {
        LaboralExperienceResponseDTO response = laboralExperienceService.getExperienceByExternalId(externalId);
        return ResponseEntity.ok(response); // Devuelve estado 200 OK con el detalle
    }

    @GetMapping("/candidate/{candidateId}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    @Operation(summary = "List all experiences belonging to a candidate", description = "Retrieves the complete work history list mapped under a single candidate account identifier.")
    @ApiResponse(responseCode = "200", description = "Candidate laboral background history list retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Candidate profile context not found")
    // Se consume pasando el ID por la URL
    public ResponseEntity<List<LaboralExperienceResponseDTO>> getExperiencesByCandidate(
            @Parameter(description = "Unique candidate ID identifier to fetch experiences from") @PathVariable UUID candidateId) {
        List<LaboralExperienceResponseDTO> response = laboralExperienceService.getExperiencesByCandidate(candidateId);
        return ResponseEntity.ok(response); // Devuelve la lista completa con estado 200 OK
    }

}