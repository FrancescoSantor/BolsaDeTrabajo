package com.Grupo15.BolsaDeTrabajo.Features.Offer;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Offers", description = "Endpoints for managing job publications, filters, and lifecycle statuses")
public class OfferController {

    private final OfferService offerService;

    //Creamos oferta
    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Create a new job offer", description = "Allows an authenticated company profile to publish a new job position vacancy.")
    @ApiResponse(responseCode = "201", description = "Job offer created successfully")
    @ApiResponse(responseCode = "400", description = "Required parameters missing or invalid salary range constraints")
    @ApiResponse(responseCode = "403", description = "Access denied. Restricted to Company role account context")
    @ApiResponse(responseCode = "404", description = "Target company reference entity not found")
    public ResponseEntity<OfferResponseDTO> createOffer(@Valid @RequestBody OfferRequestDTO requestDto) {
        OfferResponseDTO response = offerService.createOffer(requestDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);//Devuelve un estado 201 CREATED con el objeto JSON
    }

    //Modificar oferta
    @PutMapping("/{externalId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    @Operation(summary = "Update job offer metrics", description = "Modifies text descriptors and core properties of a specific active job listing via its secure public UUID.")
    @ApiResponse(responseCode = "200", description = "Job offer updated successfully")
    @ApiResponse(responseCode = "400", description = "Job offer is already closed or salary criteria is inconsistent")
    @ApiResponse(responseCode = "403", description = "Access denied. Action restricted to Admin or the actual Company listing owner")
    @ApiResponse(responseCode = "404", description = "Job offer entity context not found")
    public ResponseEntity<OfferResponseDTO> updateOffer(
            @Parameter(description = "Secure public UUID of the job offer entry") @PathVariable UUID externalId,
            @RequestBody OfferRequestDTO requestDto,
            Authentication authentication) {

        OfferResponseDTO response = offerService.updateOffer(externalId, requestDto, authentication);
        return ResponseEntity.ok(response);
    }

    //Eliminar de forma lógica (Cerrar) una oferta
    @DeleteMapping("/{externalId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'ADMIN')")
    @Operation(summary = "Close a job offer (Logical delete)", description = "Performs a business logical delete cycle by modifying the offer status flag property to CLOSED.")
    @ApiResponse(responseCode = "204", description = "Job offer marked as closed successfully")
    @ApiResponse(responseCode = "400", description = "Job offer was already closed")
    @ApiResponse(responseCode = "403", description = "Access denied. Ownership validation failed")
    @ApiResponse(responseCode = "404", description = "Job offer record not found")
    public ResponseEntity<Void> deleteOffer(
            @Parameter(description = "Secure public UUID of the job offer entry to close") @PathVariable UUID externalId,
            Authentication authentication) {

        offerService.deleteOffer(externalId, authentication);
        return ResponseEntity.noContent().build();
    }

    //Obtener el detalle de una única oferta por su UUID seguro
    @GetMapping("/{externalId}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    @Operation(summary = "Get single job offer by secure identifier", description = "Fetches the full parameter values of a single open job vacancy using its external public UUID profile identifier.")
    @ApiResponse(responseCode = "200", description = "Job offer metrics retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Job offer not found or historical status is currently set to CLOSED")
    public ResponseEntity<OfferResponseDTO> getOfferById(
            @Parameter(description = "Secure public UUID of the target job offer") @PathVariable UUID externalId) {
        OfferResponseDTO response = offerService.getOfferById(externalId);
        return ResponseEntity.ok(response); // Devuelve un estado 200 OK con el detalle de la oferta
    }

    //Listar ofertas
    @GetMapping
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    @Operation(summary = "List all active job offers (Paginated)", description = "Retrieves a paginated list of all active vacancies with status OPEN, with an optional filter matching title specifications.")
    @ApiResponse(responseCode = "200", description = "Paginated results page container retrieved successfully")
    public ResponseEntity<Page<OfferResponseDTO>> getOffers(
            // @PageableDefault configura valores por defecto si el frontend no los envía (Página 0, tamaño de 10 elementos)
            @Parameter(description = "Pagination configuration sorting metadata values") @PageableDefault(page = 0, size = 10) Pageable pageable,
            // Permite recibir un filtro opcional por parámetro en la URL (?title=ENGINEER)
            @Parameter(description = "Optional enum category title to filter results query") @RequestParam(required = false) TitleOfOffer titleOfOffer) {

        // Llama al service pasándole la información de paginación y el filtro de título
        Page<OfferResponseDTO> response = offerService.getOffers(pageable, titleOfOffer);
        return ResponseEntity.ok(response); // Devuelve la página completa de resultados con estado 200 OK
    }

}