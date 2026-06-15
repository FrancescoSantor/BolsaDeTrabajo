package com.Grupo15.BolsaDeTrabajo.Features.Roles;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RolesEntity,Long> {

    Optional<RolesEntity> findByRol (Roles rol);

}
