package com.Grupo15.BolsaDeTrabajo.Features.Saved.Controller;

import com.Grupo15.BolsaDeTrabajo.Features.Saved.Service.SavedService;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedCandidateRequestDto;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedResponseDTO;
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
@RequestMapping("/api/v1/saved")
@PreAuthorize("hasRole('COMPANY')")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
public class SavedController {

    private final SavedService savedService;

    @PostMapping
    public ResponseEntity<SavedResponseDTO> createSaved(@Valid @RequestBody SavedRequestDTO request) {
        SavedResponseDTO response = savedService.createSaved(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/candidate")
    public ResponseEntity<SavedResponseDTO> saveCandidate(@Valid @RequestBody SavedCandidateRequestDto request) {
        SavedResponseDTO response = savedService.saveCandidate(request.companyId(), request.candidateId());
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}
