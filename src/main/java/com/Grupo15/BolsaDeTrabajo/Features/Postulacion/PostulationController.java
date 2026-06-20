package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO.PostulationNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO.PostulationResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/postulation")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
public class PostulationController {

    private final PostulationService postulationService;

    @PostMapping
    public ResponseEntity<PostulationResponseDTO> create(@Valid @RequestBody PostulationNewDTO newDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(postulationService.CreatePostulation(newDTO));
    }

    @PatchMapping("/{externalId}/status")
    public ResponseEntity<PostulationResponseDTO> updateStatus(
            @PathVariable UUID externalId,
            @RequestParam PostulationState state) {

        return ResponseEntity.status(HttpStatus.OK).body(postulationService.updateStatusPostulation(externalId, state));
    }

    @GetMapping
    public ResponseEntity<List<PostulationResponseDTO>> getWithFilters(
            @RequestParam(required = false) UUID candidateId,
            @RequestParam(required = false) UUID offerId,
            @RequestParam(required = false) PostulationState state) {
        return ResponseEntity.status(HttpStatus.OK).body(postulationService.findBy(candidateId,offerId,state));
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<Void> delete(@PathVariable UUID externalId) {
        postulationService.Delete(externalId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build(); // Retorna 204 No Content (estándar para DELETE exitosos)
    }
}