package com.Grupo15.BolsaDeTrabajo.Features.Comments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record CommentsNewDTO(
        @NotNull
        UUID post_externalId,
        @NotNull
        UUID user_externalId,
        @NotBlank @Length(min = 1)
        String content

) {
}
