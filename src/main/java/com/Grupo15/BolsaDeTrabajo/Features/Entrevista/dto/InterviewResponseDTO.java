package com.Grupo15.BolsaDeTrabajo.Features.Entrevista.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Entrevista.InterviewStatus;
import com.Grupo15.BolsaDeTrabajo.Features.Entrevista.Type;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class InterviewResponseDTO {
    private UUID externalId;
    private String candidateName;       // viene de application.candidate.user.name
    private String candidateLastName;   // viene de application.candidate.user.lastName
    private String offerTitle;          // viene de application.offer.title
    private String offerLocation;       // viene de application.offer.location
    private LocalDateTime date;
    private Type type;
    private String linkMeeting;
    private String feedbackCandidate;
    private InterviewStatus status;
}
