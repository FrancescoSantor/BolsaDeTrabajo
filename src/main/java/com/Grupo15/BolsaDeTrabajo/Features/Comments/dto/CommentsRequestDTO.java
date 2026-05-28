package com.Grupo15.BolsaDeTrabajo.Features.Comments.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentsRequestDTO{
    Long postId;
    Long userId;
    String content;
}