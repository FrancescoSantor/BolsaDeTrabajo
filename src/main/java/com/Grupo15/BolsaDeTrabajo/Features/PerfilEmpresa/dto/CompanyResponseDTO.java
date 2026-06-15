package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Category;

import java.util.UUID;

public record CompanyResponseDTO(
        UUID externalId,
        String name,        // viene de user.name
        String email,
        boolean estado,// viene de user.email
        String cuit,
        Category category,
        String description,
        String webSite
) {
}
