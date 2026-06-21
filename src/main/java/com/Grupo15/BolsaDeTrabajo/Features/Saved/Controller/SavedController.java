package com.Grupo15.BolsaDeTrabajo.Features.Saved.Controller;

import com.Grupo15.BolsaDeTrabajo.Features.Saved.Service.SavedService;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedCandidateRequestDto;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BolsaDeTrabajo/saved")
@RequiredArgsConstructor
@Tag(name = "Saved Items", description = "Endpoints for bookmarks, allowing candidates to save job offers and companies to save candidate profiles")
public class SavedController {

    private final SavedService savedService;

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    @Operation(summary = "Save a job offer", description = "Allows an authenticated candidate to bookmark a job vacancy offer for future reference.")
    @ApiResponse(responseCode = "201", description = "Job offer bookmarked and saved successfully")
    @ApiResponse(responseCode = "400", description = "Business rule constraint broken or offer already saved by this candidate")
    @ApiResponse(responseCode = "403", description = "Access denied. Restricted to Candidate role permissions context")
    @ApiResponse(responseCode = "404", description = "Target candidate or job offer context record not found")
    public ResponseEntity<SavedResponseDTO> createSaved(@Valid @RequestBody SavedRequestDTO request) {
        SavedResponseDTO response = savedService.createSaved(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/candidate")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Save a candidate profile", description = "Allows an authenticated company recruiter to save a candidate's professional profile ledger.")
    @ApiResponse(responseCode = "201", description = "Candidate profile pinned and saved successfully")
    @ApiResponse(responseCode = "400", description = "Business rule validation failure or candidate already saved by this company")
    @ApiResponse(responseCode = "403", description = "Access denied. Restricted to Company role permissions context")
    @ApiResponse(responseCode = "404", description = "Target company or candidate profile context data records not found")
    public ResponseEntity<SavedResponseDTO> saveCandidate(@Valid @RequestBody SavedCandidateRequestDto request) {
        SavedResponseDTO response = savedService.saveCandidate(request.companyId(), request.candidateId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}