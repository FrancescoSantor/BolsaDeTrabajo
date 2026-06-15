package com.Grupo15.BolsaDeTrabajo.Features.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolesRepository extends JpaRepository<RolesEntity,Long> {

    Optional<RolesEntity> findByNombre(String nombre);
}
