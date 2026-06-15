package com.Grupo15.BolsaDeTrabajo.Features.Comments.mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;

import java.sql.Timestamp;
import java.time.Instant;

public class CommentsMapper {

    public static CommentsResponseDTO toResponse (CommentsEntity comments) {
        return CommentsResponseDTO.builder()
                .externalId(comments.getExternalId())
                .userName(comments.getUser().getName())
                .content(comments.getContent())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }

    public static CommentsEntity toEntity (CommentsRequestDTO request) {
        return CommentsEntity.builder()
                .content(request.getContent())
                .createdAt(Timestamp.from(Instant.now()))
                .build();
    }
}
