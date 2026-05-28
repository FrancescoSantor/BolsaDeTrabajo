package com.Grupo15.BolsaDeTrabajo.Features.Interview.dto;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.Type;

import java.time.LocalDateTime;

public record InterviewRequestDTO(
        Long applicationId,
        LocalDateTime date,
        Type type,
        String linkMeeting,
        String companyNotes
) {}
