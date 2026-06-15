package com.Grupo15.BolsaDeTrabajo.Features.auth.permissions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermitRepository extends JpaRepository <PermitEntity , Long> {
    PermitEntity Permit(Permits permit);

}
