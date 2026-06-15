package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Category;

import java.util.UUID;

public record CompaniesRequestDTO(
        String name,
        String cuit,
        UUID externalID
) {}