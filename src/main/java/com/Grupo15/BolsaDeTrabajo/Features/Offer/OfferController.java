package com.Grupo15.BolsaDeTrabajo.Features.Offer;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/BolsaDeTrabajo/offers") //Aca hay que ver si Definimos bien la ruta
@RequiredArgsConstructor
public class OfferController {

    private final OfferService offerService;

    //Creamos oferta
    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<OfferResponseDTO> createOffer(@Valid @RequestBody OfferRequestDTO requestDto) {
        OfferResponseDTO response = offerService.createOffer(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);//Devuelve un estado 201 CREATED con el objeto JSON
    }

    //Modificar oferta
    @PutMapping("/{externalId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    public ResponseEntity<OfferResponseDTO> updateOffer(
            @PathVariable UUID externalId,
            @RequestBody OfferRequestDTO requestDto,
            Authentication authentication) {

        OfferResponseDTO response = offerService.updateOffer(externalId, requestDto, authentication);
        return ResponseEntity.ok(response);
    }

    //Eliminar de forma lógica (Cerrar) una oferta
    @DeleteMapping("/{externalId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    public ResponseEntity<Void> deleteOffer(
            @PathVariable UUID externalId,
            Authentication authentication) {

        offerService.deleteOffer(externalId, authentication);
        return ResponseEntity.noContent().build();
    }

    //Obtener el detalle de una única oferta por su UUID seguro
    @GetMapping("/{externalId}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    public ResponseEntity<OfferResponseDTO> getOfferById(@PathVariable UUID externalId) {
        OfferResponseDTO response = offerService.getOfferById(externalId);
        return ResponseEntity.ok(response); // Devuelve un estado 200 OK con el detalle de la oferta
    }

    //Listar ofertas
    @GetMapping
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    public ResponseEntity<Page<OfferResponseDTO>> getOffers(
            // @PageableDefault configura valores por defecto si el frontend no los envía (Página 0, tamaño de 10 elementos)
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            // Permite recibir un filtro opcional por parámetro en la URL (?title=ENGINEER)
            @RequestParam(required = false) TitleOfOffer titleOfOffer) {

        // Llama al service pasándole la información de paginación y el filtro de título
        Page<OfferResponseDTO> response = offerService.getOffers(pageable, titleOfOffer);
        return ResponseEntity.ok(response); // Devuelve la página completa de resultados con estado 200 OK
    }

}
