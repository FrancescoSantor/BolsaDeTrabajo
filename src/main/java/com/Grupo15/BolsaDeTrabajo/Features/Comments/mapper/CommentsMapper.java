package com.Grupo15.BolsaDeTrabajo.Features.Comments.mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentsMapper {

    @Mapping(target = "id",ignore = true)
    @Mapping(target = "post",ignore = true)
    @Mapping(target = "user",ignore = true)
    @Mapping(target = "createdAt",ignore = true)
    @Mapping(target = "Active",ignore = true)
    CommentsEntity toEntity(CommentsNewDTO newDTO);

}
