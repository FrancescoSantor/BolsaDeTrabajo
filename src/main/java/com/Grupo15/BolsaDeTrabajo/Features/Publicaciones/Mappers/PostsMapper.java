package com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.Mappers;

import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.PostsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.dto.PostsRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.dto.PostsResponseDTO;

public class PostsMapper {
    public static PostsResponseDTO toResponse (
            PostsEntity post,
            String companyName,
            String companyLocation,
            String offerTitle) {
        return PostsResponseDTO.builder()
                .externalId(post.getExternalId())
                .companyName(companyName)
                .companyLocation(companyLocation)
                .offerTitle(offerTitle)
                .title(post.getTitle())
                .content(post.getContent())
                .urlImage(post.getUrlImage())
                .totalLikes(post.getTotalLikes())
                .totalComments(post.getTotalComments())
                .active(post.isActive())
                .createdAt(post.getCreatedAt())
                .updatedAt(post.getUpdatedAt())
                .build();
    }

    public static PostsEntity toRequest (PostsRequestDTO request) {
        return PostsEntity.builder()
                .title(request.title())
                .content(request.content())
                .urlImage(request.urlImage())
                .build();
    }
}
