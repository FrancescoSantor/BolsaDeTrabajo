package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Category;

import java.util.UUID;

public record CompaniesRequestDTO(
        String name,
        String email,
        String cuit,
        Category category,
        String description,
        String webSite,
        String location
) {}