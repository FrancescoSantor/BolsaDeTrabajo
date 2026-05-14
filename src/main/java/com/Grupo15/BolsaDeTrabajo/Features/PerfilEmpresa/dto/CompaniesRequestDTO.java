package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Category;

public record CompaniesRequestDTO(
        Long userId,
        String registeredName,
        String cuit,
        Category category,
        String description,
        String webSite,
        String location
) {}