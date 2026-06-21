package com.Grupo15.BolsaDeTrabajo.Features.Ability;


import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/BolsaDeTrabajo/abilities")
@RequiredArgsConstructor
@Tag(name = "Abilities", description = "Endpoints for managing and retrieving user skills and abilities")
public class AbilityController {

    private final AbilityService abilityService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a new ability", description = "Allows an administrator to register a new unique ability in the system.")
    @ApiResponse(responseCode = "201", description = "Ability created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request payload")
    @ApiResponse(responseCode = "403", description = "Access denied. Admin role required")
    @ApiResponse(responseCode = "409", description = "Ability name already exists")
    public AbilityResponseDTO createAbility(
            @Valid @RequestBody AbilityRequestDTO dto) {

        return abilityService.createAbility(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get all abilities", description = "Retrieves a complete list of all registered abilities in the database.")
    @ApiResponse(responseCode = "200", description = "List of abilities retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    public List<AbilityResponseDTO> getAllAbilities() {

        return abilityService.getAllAbilities();
    }

    @GetMapping("/{externalId}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get an ability by its ID", description = "Fetches the detailed information of a specific ability using its unique external UUID.")
    @ApiResponse(responseCode = "200", description = "Ability found and returned successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Ability not found with the provided ID")
    public AbilityResponseDTO getAbilityById(
            @Parameter(description = "Unique external UUID of the ability")@PathVariable UUID externalId) {

        return abilityService.getAbilityByExternalId(externalId);
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get abilities by category", description = "Filters and retrieves a list of abilities belonging to a specific category.")
    @ApiResponse(responseCode = "200", description = "Abilities retrieved successfully for the given category")
    @ApiResponse(responseCode = "403", description = "Access denied")
    public List<AbilityResponseDTO> getAbilitiesByCategory(
            @Parameter(description = "Category enum value to filter by")@PathVariable AbilityCategory category) {

        return abilityService.getAllAbilitiesByCategory(category);
    }



    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search abilities by name", description = "Finds all abilities whose names match or contain the provided query string")
    @ApiResponse(responseCode = "200", description = "Search results retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    public List<AbilityResponseDTO> searchAbility(
            @Parameter(description = "Name or substring to search for") @RequestParam String name) {

        return abilityService.searchAbilityByName(name);
    }

    @DeleteMapping("/{externalId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an ability", description = "Allows an administrator to permanently delete an ability by its unique external UUID.")
    @ApiResponse(responseCode = "204", description = "Ability deleted successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Admin role required")
    @ApiResponse(responseCode = "404", description = "Ability not found")
    public void deleteAbility(
            @Parameter(description = "Unique external UUID of the ability to delete") @PathVariable UUID externalId) {

        abilityService.deleteAbility(externalId);
    }
}