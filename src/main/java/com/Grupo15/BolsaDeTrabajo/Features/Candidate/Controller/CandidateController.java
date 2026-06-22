package com.Grupo15.BolsaDeTrabajo.Features.Candidate.Controller;

import com.Grupo15.BolsaDeTrabajo.Features.Ability.AbilityCategory;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Service.CandidateService;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/BolsaDeTrabajo/candidate")
@RequiredArgsConstructor
@Tag(name = "Candidates", description = "Endpoints for managing candidate profiles, account deactivation, and matching abilities")
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidatesResponseDTO> create(
            @Valid @RequestBody CandidatesRequestDTO request) {

        CandidatesResponseDTO responseDto = candidateService.creteCandidate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    @Operation(summary = "Get all active candidates", description = "Retrieves a flat list containing all candidate profiles currently active in the system.")
    @ApiResponse(responseCode = "200", description = "List of candidates retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Restricted to Company or Admin roles")
    public ResponseEntity<List<CandidatesResponseDTO>> listAllCandidates() {
        List<CandidatesResponseDTO> response = candidateService.listAllCandidates();
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    @Operation(summary = "inactivate a candidate profile", description = "Performs a logical delete by updating the candidate's active state status to false.")
    @ApiResponse(responseCode = "204", description = "Candidate account deactivated successfully")
    @ApiResponse(responseCode = "403", description = "Insufficient permissions to execute this request")
    @ApiResponse(responseCode = "404", description = "Candidate profile not found or already inactive")
    public ResponseEntity<Void> deleteCandidate(
            @Parameter(description = "Unique UUID identifier of the candidate profile") @PathVariable UUID id) {

        candidateService.deleteCandidate(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY', 'ADMIN')")
    @Operation(summary = "Get a candidate profile by ID", description = "Fetches complete public details of a specific candidate using their unique identifier.")
    @ApiResponse(responseCode = "200", description = "Candidate profile details retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Insufficient access permissions")
    @ApiResponse(responseCode = "404", description = "Candidate profile not found or account is currently disabled")
    public ResponseEntity<CandidatesResponseDTO> getCandidate(
            @Parameter(description = "Unique UUID identifier of the candidate profile") @PathVariable UUID id) {
        CandidatesResponseDTO responseDto = candidateService.getCandidate(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Update candidate details", description = "Allows an authenticated candidate to modify their own profile data fields.")
    @ApiResponse(responseCode = "200", description = "Candidate profile properties updated successfully")
    @ApiResponse(responseCode = "400", description = "Validation failed for input parameters")
    @ApiResponse(responseCode = "403", description = "Access denied. Action is restricted to the account owner")
    @ApiResponse(responseCode = "404", description = "Candidate profile entity not found")
    public ResponseEntity<CandidatesResponseDTO> updateCandidate(
            @Parameter(description = "Unique UUID identifier of the candidate profile") @PathVariable UUID id,
            @Valid @RequestBody CandidatesRequestDTO request) {

        CandidatesResponseDTO responseDto = candidateService.updateCandidate(id, request);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/{id}/abilities")
    @PreAuthorize("hasAnyRole('CANDIDATE')")
    @Operation(summary = "Link an ability to candidate", description = "Assigns a specific category ability link to the designated candidate account profile.")
    @ApiResponse(responseCode = "200", description = "Ability mapped successfully to the candidate profile")
    @ApiResponse(responseCode = "403", description = "Action forbidden for other accounts")
    @ApiResponse(responseCode = "404", description = "Candidate or skill category matching data not found")
    public ResponseEntity<Void> addAbility(
            @Parameter(description = "Unique UUID identifier of the candidate profile") @PathVariable UUID id,
            @Parameter(description = "Target skill enum category to assign") @RequestParam AbilityCategory category,
            Authentication authentication) {

        candidateService.addAbilityToCandidate(id, category, authentication);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @DeleteMapping("/{id}/abilities")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    @Operation(summary = "Remove an ability from candidate", description = "Unlinks a specific category ability mapping from the target candidate's profile.")
    @ApiResponse(responseCode = "204", description = "Ability successfully removed from the candidate account")
    @ApiResponse(responseCode = "403", description = "Action forbidden")
    @ApiResponse(responseCode = "404", description = "Candidate matching relation or skill group not found")
    public ResponseEntity<Void> deleteAbility(
            @Parameter(description = "Unique UUID identifier of the candidate profile") @PathVariable UUID id,
            @Parameter(description = "Target skill enum category to unassign") @RequestParam AbilityCategory category,
            Authentication authentication) {

        candidateService.deleteAbilityFromCandidate(id, category, authentication);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}