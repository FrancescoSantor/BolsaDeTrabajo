package com.Grupo15.BolsaDeTrabajo.Features.Ability;

import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IAbilityService {

    AbilityResponseDTO createAbility(AbilityRequestDTO requestAbility);
    AbilityResponseDTO getAbilityByExternalId(UUID externalId);
    List<AbilityResponseDTO> getAllAbilities();
    List<AbilityResponseDTO> getAllAbilitiesByCategory(AbilityCategory category);
    void deleteAbility(UUID externalIdAbility);
    List<AbilityResponseDTO> searchAbilityByName(String name);




}
