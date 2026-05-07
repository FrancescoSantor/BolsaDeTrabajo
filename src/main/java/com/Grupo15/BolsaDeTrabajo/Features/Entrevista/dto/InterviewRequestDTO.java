package com.Grupo15.BolsaDeTrabajo.Features.Entrevista.dto;
import com.Grupo15.BolsaDeTrabajo.Features.Entrevista.Type;

import java.time.LocalDateTime;

public record InterviewRequestDTO(
        Long applicationId,
        LocalDateTime date,
        Type type,
        String linkMeeting,
        String companyNotes
) {}
