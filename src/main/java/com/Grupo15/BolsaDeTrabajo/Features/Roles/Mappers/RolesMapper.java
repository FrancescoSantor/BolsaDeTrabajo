package com.Grupo15.BolsaDeTrabajo.Features.Roles.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.dto.RolesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.dto.RolesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.dto.RolesResponseDTO;

public class RolesMapper {

    public static RolesResponseDTO toResponse (RolesEntity rol) {
        return RolesResponseDTO.builder()
                .externalId(rol.getExternalId())
                .rol(rol.getRol())
                .build();
    }

    public static RolesEntity toRequest (RolesRequestDTO request) {
        return RolesEntity.builder()
                .rol(request.rol())
                .build();
    }
}
