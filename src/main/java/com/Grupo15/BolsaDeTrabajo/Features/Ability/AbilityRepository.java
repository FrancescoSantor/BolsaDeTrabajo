package com.Grupo15.BolsaDeTrabajo.Features.Ability;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface AbilityRepository extends JpaRepository<AbilityEntity,Long> {

    List<AbilityEntity> findByNameIgnoreCase (String name);
   // Optional<AbilityEntity>findByNameIgnoreCase (String name);
    List <AbilityEntity> findByCategory (AbilityCategory category);
    boolean existsByNameIgnoreCase (String name);
    Optional<AbilityEntity> findByExternalId(UUID externalId);
}
