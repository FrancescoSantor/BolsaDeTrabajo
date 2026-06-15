package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto;

import org.springframework.cglib.core.Local;

import java.sql.Date;
import java.time.LocalDate;

public record LaboralExperienceRequestDTO(
        Long candidateId,
        String company,
        String position,
        LocalDate initialDate,
        LocalDate endDate,
        String description
) {}