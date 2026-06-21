package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Category;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;

public record CompaniesRequestDTO(
        @NotNull
        UUID externalId,
        @NotBlank(message = "User name is required")
        String name,
        @NotBlank(message = "Email is required")
        @Email(message = "Email Format is not valid")
        String email,
        @NotBlank(message = "cuit is required")
        @Length(min = 9, message = "cuit must be at least 9 characters long")
        String cuit,
        @NotNull(message = "category is required")
        Category category,
        @NotBlank(message = "description was required")
        String description,
        @NotBlank(message = "Web site is required")
        String webSite,
        @NotBlank(message = "location is required")
        String location
) {}