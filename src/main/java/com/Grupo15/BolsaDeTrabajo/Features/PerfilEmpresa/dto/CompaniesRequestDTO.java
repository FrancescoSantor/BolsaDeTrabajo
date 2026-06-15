package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto;


import java.util.UUID;

public record CompaniesRequestDTO(
        String name,
        String cuit,
        UUID idPublicaciones

) {}