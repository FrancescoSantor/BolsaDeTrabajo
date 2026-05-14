package com.Grupo15.BolsaDeTrabajo.Features.Roles.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Roles.Roles;
import lombok.Data;
import java.util.UUID;

@Data
public class RolesResponseDTO {
    private UUID externalId;
    private Roles rol;
}