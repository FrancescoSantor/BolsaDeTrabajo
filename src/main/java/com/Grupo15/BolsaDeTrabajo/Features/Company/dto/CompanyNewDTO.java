package com.Grupo15.BolsaDeTrabajo.Features.Company.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Company.Category;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CompanyNewDTO(
        @NotBlank(message = "The name is required")
        String name,
        @NotBlank (message = "Username is required")
        String username,
        @NotBlank(message = "Email is required")
        @Email(message = "Email Format is not valid")
        String email,
        @NotBlank(message = "Password is Required")
        @Length(min = 8, message = "password must be at least 8 characters long")
        String password,
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
) {
}
