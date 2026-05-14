package com.Grupo15.BolsaDeTrabajo.Features.Seguimientos.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.Seguimientos.FollowingsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Seguimientos.dto.FollowingsRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Seguimientos.dto.FollowingsResponseDTO;

public class FollowingsMapper {

    public static FollowingsResponseDTO toResponse (
            FollowingsEntity following,
            String userName,
            String userLastName,
            String companyName,
            String companyLocation) {

        return FollowingsResponseDTO.builder()
                .externalId(following.getExternalId())
                .userName(userName)
                .userLastName(userLastName)
                .companyName(companyName)
                .companyLocation(companyLocation)
                .createdAt(following.getCreatedAt())
                .build();
    }

    public static FollowingsEntity toRequest (FollowingsRequestDTO request) {
        return FollowingsEntity.builder().build();
    }
}
