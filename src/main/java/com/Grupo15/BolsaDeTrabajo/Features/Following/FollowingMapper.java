package com.Grupo15.BolsaDeTrabajo.Features.Following;

import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingsRequestDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FollowingMapper {
    public FollowingsEntity toEntity (FollowingsRequestDTO requestDTO);

    public FollowingResponseDTO toDto (FollowingsEntity following);
}
