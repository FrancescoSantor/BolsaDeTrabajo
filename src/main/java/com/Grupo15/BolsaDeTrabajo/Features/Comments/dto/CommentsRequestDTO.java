package com.Grupo15.BolsaDeTrabajo.Features.Comments.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentsRequestDTO{
    Long postId;
    //Long userId;  se puede sacar desde el contexto de que el usuario en si ya esta logeado
    String content;
}