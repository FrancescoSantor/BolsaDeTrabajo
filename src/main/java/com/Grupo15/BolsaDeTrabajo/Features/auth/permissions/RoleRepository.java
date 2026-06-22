package com.Grupo15.BolsaDeTrabajo.Features.auth.permissions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity , Long> {

    Optional<RoleEntity> findByRole(Role role);
}
