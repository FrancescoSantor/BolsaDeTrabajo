package com.Grupo15.BolsaDeTrabajo.Features.Users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UsersEntity,Long> {
}
