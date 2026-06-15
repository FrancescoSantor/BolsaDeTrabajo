package com.Grupo15.BolsaDeTrabajo.Features.auth.permissions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity , Long> {
}
