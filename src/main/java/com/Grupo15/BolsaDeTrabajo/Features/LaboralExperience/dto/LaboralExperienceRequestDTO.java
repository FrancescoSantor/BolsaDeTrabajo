package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDate;
import java.util.UUID;

public record LaboralExperienceRequestDTO(
        @NotNull(message = "El ID del candidato es obligatorio.")
        UUID candidateId,

        @NotBlank(message = "El nombre de la empresa no puede estar vacío.")
        @Size(max = 100, message = "El nombre de la empresa no puede superar los 100 caracteres.")
        String company,

        @NotBlank(message = "El puesto de trabajo no puede estar vacío.")
        @Size(max = 100, message = "El puesto no puede superar los 100 caracteres.")
        String position,

        @NotNull(message = "La fecha de inicio es obligatoria.")
        @PastOrPresent(message = "La fecha de inicio no puede ser una fecha futura.")
        LocalDate initialDate,


        LocalDate endDate,

        @NotBlank(message = "La descripción de las tareas es obligatoria.")
        @Size(max = 2000, message = "La descripción no puede superar los 2000 caracteres.")
        String description
) {}