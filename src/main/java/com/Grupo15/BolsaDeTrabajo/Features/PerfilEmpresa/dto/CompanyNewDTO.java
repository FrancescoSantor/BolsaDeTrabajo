package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Category;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.Roles;

public record CompanyNewDTO(
        String name,
        String email,
        String password,
        String username,
        String cuit,
        Category category,
        String description,
        String webSite,
        String location
) {
}
