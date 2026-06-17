package com.Grupo15.BolsaDeTrabajo.Features.Candidate.Controller;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Service.CandidateService;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/candidate")
@RequiredArgsConstructor

public class CandidateController {

    private final CandidateService candidateService;

    //Agregar
    @PostMapping
    public ResponseEntity<CandidatesResponseDTO> create(@Valid @RequestBody CandidatesRequestDTO request) {
        CandidatesResponseDTO responseDto = candidateService.creteCandidate(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    //Eliminar (logico)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidate(@PathVariable Long id) {
        candidateService.deleteCandidate(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    //Buscar
    @GetMapping("/{id}")
    public ResponseEntity<CandidatesResponseDTO> getCandidate(@PathVariable Long id) {
        CandidatesResponseDTO responseDto = candidateService.getCandidate(id);
        return ResponseEntity.ok(responseDto);
    }

    //Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<CandidatesResponseDTO> updateCandidate(
            @PathVariable Long id,
            @Valid @RequestBody CandidatesRequestDTO request) {
        CandidatesResponseDTO responseDto = candidateService.updateCandidate(id, request);
        return ResponseEntity.ok(responseDto);
    }
}
