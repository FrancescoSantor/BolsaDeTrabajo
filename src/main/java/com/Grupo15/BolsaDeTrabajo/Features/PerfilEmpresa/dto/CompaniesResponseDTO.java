package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Category;
import lombok.Data;
import java.util.UUID;

@Data
public class CompaniesResponseDTO {
    private UUID externalId;
    private String userName;        // viene de user.name
    private String userLastName;    // viene de user.lastName
    private String userEmail;       // viene de user.email
    private String registeredName;
    private String cuit;
    private Category category;
    private String description;
    private String webSite;
    private String location;
}