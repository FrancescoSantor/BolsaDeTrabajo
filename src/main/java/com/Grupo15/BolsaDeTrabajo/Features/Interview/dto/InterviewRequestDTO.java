package com.Grupo15.BolsaDeTrabajo.Features.Interview.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Interview.Type;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record InterviewRequestDTO(

        @NotNull(message = "Application id is required")
        Long applicationId,

        @NotNull(message = "Interview date is required")
        @Future(message = "Interview date must be in the future")
        LocalDateTime date,

        @NotNull(message = "Interview type is required")
        Type type,

        @NotBlank(message = "Meeting link is required")
        String linkMeeting,

        @NotBlank(message = "Company notes are required")
        String companyNotes

) {}