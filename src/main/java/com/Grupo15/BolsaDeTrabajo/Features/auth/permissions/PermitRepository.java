package com.Grupo15.BolsaDeTrabajo.Features.auth.permissions;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PermitRepository extends JpaRepository <PermitEntity , Long> {
    Optional<PermitEntity> findByPermit(Permits permit);

}
