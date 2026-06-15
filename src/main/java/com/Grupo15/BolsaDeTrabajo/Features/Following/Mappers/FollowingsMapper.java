package com.Grupo15.BolsaDeTrabajo.Features.Following.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.Following.FollowingsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingsRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingsResponseDTO;

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
                .companyName(companyName)
                .createdAt(following.getCreatedAt())
                .build();
    }

    public static FollowingsEntity toRequest (FollowingsRequestDTO request) {
        return FollowingsEntity.builder().build();
    }
}
