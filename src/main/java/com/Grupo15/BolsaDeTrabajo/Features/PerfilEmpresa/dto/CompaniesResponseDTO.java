package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Category;
import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class CompaniesResponseDTO {
    private UUID externalId;
    private String name;        // viene de user.name
    private String lastName;    // viene de user.lastName
    private String email;       // viene de user.email
    private String registeredName;
    private String cuit;
    private Category category;
    private String description;
    private String webSite;
}