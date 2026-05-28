package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto;

import java.sql.Date;

public record LaboralExperienceRequestDTO(
        Long candidateId,
        String company,
        String position,
        Date initialDate,
        Date endDate,
        boolean currentWork,
        String description
) {}