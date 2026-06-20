package com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Title;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public record CandidatesRequestDTO(

       /* @NotNull(message = "User id is required")
        Long userId,*/

        @NotBlank(message = "Name is required")
        String name,

        @NotBlank(message = "Last name is required")
        String lastName,

        @NotBlank (message = "Username is required")
        String username,

        @NotBlank(message = "Email is required")
        @Email(message = "Email format is not valid")
        String email,

        @NotBlank(message = "Password is required")
        @Length(min = 8, message = "Password must be at least 8 characters long")
        String password,

        @NotNull(message = "Professional title is required")
        Title professionalTitle,

        @NotBlank(message = "Summary is required")
        String summary,

        @NotBlank(message = "CV URL is required")
        String cvUrl,

        @NotBlank(message = "LinkedIn URL is required")
        String linkedinUrl,

        String photoUrl

) {}