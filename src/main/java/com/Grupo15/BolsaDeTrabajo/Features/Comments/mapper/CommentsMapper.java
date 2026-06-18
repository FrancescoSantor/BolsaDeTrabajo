package com.Grupo15.BolsaDeTrabajo.Features.Comments.mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentsMapper {

    CommentsEntity toEntity(CommentsNewDTO newDTO);

    CommentsResponseDTO toDTO(CommentsEntity entity);

}
