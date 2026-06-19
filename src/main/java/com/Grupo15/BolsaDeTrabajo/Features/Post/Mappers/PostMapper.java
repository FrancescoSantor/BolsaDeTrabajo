package com.Grupo15.BolsaDeTrabajo.Features.Post.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.Post.PostsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostsRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PostMapper {
    @Mapping(target = "id" , ignore = true)
    @Mapping(target = "company" , ignore = true)
    @Mapping(target = "offer" , ignore = true)
    @Mapping(target = "totalLikes" , ignore = true)
    @Mapping(target = "totalComments" , ignore = true)
    @Mapping(target = "active" , ignore = true)
    @Mapping(target = "createdAt" , ignore = true)
    @Mapping(target = "updatedAt" , ignore = true)
    @Mapping(target = "likes" , ignore = true)
    @Mapping(target = "comments" , ignore = true)
    public PostsEntity toEntity(PostsRequestDTO requestDTO);

    @Mapping(target = "companyName", source = "company.name")
    @Mapping(target = "offerTitle", source = "offer.title")
    public PostResponseDTO toDto(PostsEntity post);
}
