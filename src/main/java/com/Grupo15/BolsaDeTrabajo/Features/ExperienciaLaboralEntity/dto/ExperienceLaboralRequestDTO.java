package com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.dto;

import java.sql.Date;

public record ExperienceLaboralRequestDTO(
        Long candidateId,
        String company,
        String position,
        Date initialDate,
        Date endDate,
        boolean currentWork,
        String description
) {}