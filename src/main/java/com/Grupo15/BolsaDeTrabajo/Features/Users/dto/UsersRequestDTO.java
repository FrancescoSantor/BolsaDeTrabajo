package com.Grupo15.BolsaDeTrabajo.Features.Users.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Roles.Roles;

public record UsersRequestDTO(
        String name,
        String lastName,
        String email,
        String password,
        Roles rol
) {}