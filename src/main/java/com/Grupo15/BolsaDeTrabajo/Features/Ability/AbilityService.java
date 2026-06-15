package com.Grupo15.BolsaDeTrabajo.Features.Ability;

import com.Grupo15.BolsaDeTrabajo.Features.Ability.Mappers.AbilityMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Ability.dto.AbilityResponseDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AbilityService {

    private final AbilityRepository repositoryAbility;
    private final AbilityMapper mapperAbility;



    @Transactional
    public AbilityResponseDTO createAbility(AbilityRequestDTO requestAbility) {

        if (repositoryAbility.existsByNameIgnoreCase(requestAbility.name())) {
            throw new EntityExistsException(
                    "The ability " + requestAbility.name() + " already exists");
        }

        AbilityEntity newAbility = mapperAbility.toEntity(requestAbility);

        AbilityEntity saved = repositoryAbility.save(newAbility);

        return mapperAbility.toDto(saved);
    }


    public AbilityResponseDTO getAbilityByExternalId(UUID externalId) {
        AbilityEntity ability = repositoryAbility.findByExternalId(externalId)
                .orElseThrow(() ->
                        new EntityNotFoundException("The ability : " + externalId + " was not found"));

        return mapperAbility.toDto(ability);
    }

    public List<AbilityResponseDTO> getAllAbilities() {
        return repositoryAbility.findAll().stream().map(mapperAbility::toDto).toList();
    }


    public List<AbilityResponseDTO> getAllAbilitiesByCategory(AbilityCategory category) {

        List<AbilityEntity> abilities =
                repositoryAbility.findByCategory(category);


        return abilities.stream()
                .map(mapperAbility::toDto)
                .toList();
    }


    @Transactional
    public void deleteAbility(UUID externalIdAbility) {
        AbilityEntity ability = repositoryAbility.findByExternalId(externalIdAbility)
                .orElseThrow(() ->
                        new EntityNotFoundException("Ability not found"));

        repositoryAbility.delete(ability);
    }

    public List<AbilityResponseDTO> searchAbilityByName(String name) {
        List<AbilityEntity> abilities = repositoryAbility.findByNameContainingIgnoreCase(name);


        return abilities.stream().map(mapperAbility::toDto).toList();
    }
}
