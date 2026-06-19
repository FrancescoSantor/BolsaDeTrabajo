package com.Grupo15.BolsaDeTrabajo.Features.Following;

import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingsRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface FollowingMapper {
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "state",ignore = true)
    @Mapping(target = "follower",ignore = true)
    @Mapping(target = "followed",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    FollowingsEntity toEntity (FollowingsRequestDTO requestDTO);


    @Mapping(target = "userName", source = "follower.name")
    @Mapping(target = "companyName", source = "followed.name")
    FollowingResponseDTO toDto (FollowingsEntity following);
}
