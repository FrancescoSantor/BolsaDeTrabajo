package com.Grupo15.BolsaDeTrabajo.Features.Candidate.Controller;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Service.CandidateService;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/BolsaDeTrabajo/candidate")
@RequiredArgsConstructor
public class CandidateController {

    private final CandidateService candidateService;

    @PostMapping
    public ResponseEntity<CandidatesResponseDTO> create(
            @Valid @RequestBody CandidatesRequestDTO request) {

        CandidatesResponseDTO responseDto = candidateService.creteCandidate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {

        candidateService.deleteCandidate(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY', 'ADMIN')")
    public ResponseEntity<CandidatesResponseDTO> getCandidate(@PathVariable Long id) {
        CandidatesResponseDTO responseDto = candidateService.getCandidate(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<CandidatesResponseDTO> updateCandidate(
            @PathVariable Long id,
            @Valid @RequestBody CandidatesRequestDTO request) {

        CandidatesResponseDTO responseDto = candidateService.updateCandidate(id, request);
        return ResponseEntity.ok(responseDto);
    }
}