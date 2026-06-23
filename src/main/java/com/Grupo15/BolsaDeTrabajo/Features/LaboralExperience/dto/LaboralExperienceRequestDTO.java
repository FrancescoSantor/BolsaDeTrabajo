package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record LaboralExperienceRequestDTO(
        @NotNull(message = "Candidate ID is required.")
        UUID candidateId,

        @NotBlank(message = "Company name cannot be empty.")
        @Size(max = 100, message = "Company name cannot exceed 100 characters.")
        String companyReferenceName,

        @NotBlank(message = "Job position cannot be empty.")
        @Size(max = 100, message = "Job position cannot exceed 100 characters.")
        String position,

        @NotNull(message = "Initial date is required.")
        @PastOrPresent(message = "Initial date cannot be a future date.")
        LocalDate initialDate,

        LocalDate endDate,

        @NotBlank(message = "Task description is required.")
        @Size(max = 2000, message = "Description cannot exceed 2000 characters.")
        String description,

        UUID companyReferenceId
) {}