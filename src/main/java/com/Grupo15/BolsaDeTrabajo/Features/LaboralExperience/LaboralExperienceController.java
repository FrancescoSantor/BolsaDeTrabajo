package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience;


import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceResponseDTO;
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
public class LaboralExperienceController {

    private final LaboralExperienceService laboralExperienceService;

    @PostMapping
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<LaboralExperienceResponseDTO> createExperience(@Valid @RequestBody LaboralExperienceRequestDTO requestDto) {
        LaboralExperienceResponseDTO response = laboralExperienceService.createExperience(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // Devuelve estado 201 CREATED
    }

    @PutMapping("/{externalId}")
    @PreAuthorize("hasRole('CANDIDATE')")// Captura el UUID de la URL   // Valida los datos editados
    public ResponseEntity<LaboralExperienceResponseDTO> updateExperience(@PathVariable UUID externalId, @Valid @RequestBody LaboralExperienceRequestDTO requestDto) {
        LaboralExperienceResponseDTO response = laboralExperienceService.updateExperience(externalId, requestDto);
        return ResponseEntity.ok(response); // Devuelve estado 200 OK con los cambios
    }


    @DeleteMapping("/{externalId}")
    @PreAuthorize("hasRole('CANDIDATE')")
    public ResponseEntity<Void> deleteExperience(@PathVariable UUID externalId) {
        laboralExperienceService.deleteExperience(externalId);
        return ResponseEntity.noContent().build(); // Devuelve estado 204 NO CONTENT
    }


    @GetMapping("/{externalId}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    public ResponseEntity<LaboralExperienceResponseDTO> getExperienceByExternalId(@PathVariable UUID externalId) {
        LaboralExperienceResponseDTO response = laboralExperienceService.getExperienceByExternalId(externalId);
        return ResponseEntity.ok(response); // Devuelve estado 200 OK con el detalle
    }

    @GetMapping("/candidate/{candidateId}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    // Se consume pasando el ID por la URL
    public ResponseEntity<List<LaboralExperienceResponseDTO>> getExperiencesByCandidate(@PathVariable UUID candidateId) {
        List<LaboralExperienceResponseDTO> response = laboralExperienceService.getExperiencesByCandidate(candidateId);
        return ResponseEntity.ok(response); // Devuelve la lista completa con estado 200 OK
    }

}