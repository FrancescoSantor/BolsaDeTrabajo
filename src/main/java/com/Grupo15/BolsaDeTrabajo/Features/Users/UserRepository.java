package com.Grupo15.BolsaDeTrabajo.Features.Users;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<UsersEntity,Long> {
    boolean existsByEmail(String email);
    Optional<UsersEntity> findByExternalId (UUID externalId);

}
