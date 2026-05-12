package com.Grupo15.BolsaDeTrabajo.Features.Users.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Roles.Roles;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;
import java.sql.Timestamp;

@Data
@Builder
public class UsersResponseDTO {
    private UUID externalId;
    private String name;
    private String lastName;
    private String email;
    private boolean active;
    private Roles rol;          // viene de rol.rol
    private Timestamp createdAt;
}