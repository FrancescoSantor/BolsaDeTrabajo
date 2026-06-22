package com.Grupo15.BolsaDeTrabajo.Features.Company.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Company.Category;

import java.util.UUID;

public record CompanyResponseDTO(
        UUID externalId,
        String name,
        String email,       // viene de user.email
        String cuit,
        Category category,
        String location,
        String description,
        String webSite) {
}
