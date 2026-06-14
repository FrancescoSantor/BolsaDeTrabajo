package com.Grupo15.BolsaDeTrabajo.Features.PostLikes.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.PostLikesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto.PostLikesResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostLikeMapper {

    @Mapping(source = "entity.externalId", target = "likeExternalId")
    @Mapping(source = "entity.user.name", target = "userName")       // Navega hasta el nombre del usuario
    @Mapping(source = "entity.post.title", target = "postTitle")     // Navega hasta el título del post
    @Mapping(source = "entity.createdAt", target = "createdAt")
    //liked y totalLikes se calcularán y setearán en el Service
    PostLikesResponseDTO toDto(PostLikesEntity entity);

    //No se hace toEntity por como se construyo el RequestDTO
}