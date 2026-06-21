package com.Grupo15.BolsaDeTrabajo.Features.Ability;


import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityResponseDTO;
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
public class AbilityController {

    private final AbilityService abilityService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.CREATED)
    public AbilityResponseDTO createAbility(
            @Valid @RequestBody AbilityRequestDTO dto) {

        return abilityService.createAbility(dto);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<AbilityResponseDTO> getAllAbilities() {

        return abilityService.getAllAbilities();
    }

    @GetMapping("/{externalId}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public AbilityResponseDTO getAbilityById(
            @PathVariable UUID externalId) {

        return abilityService.getAbilityByExternalId(externalId);
    }

    @GetMapping("/category/{category}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<AbilityResponseDTO> getAbilitiesByCategory(
            @PathVariable AbilityCategory category) {

        return abilityService.getAllAbilitiesByCategory(category);
    }



    @GetMapping("/search")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'ADMIN')")
    @ResponseStatus(HttpStatus.OK)
    public List<AbilityResponseDTO> searchAbility(
            @RequestParam String name) {

        return abilityService.searchAbilityByName(name);
    }

    @DeleteMapping("/{externalId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteAbility(
            @PathVariable UUID externalId) {

        abilityService.deleteAbility(externalId);
    }
}