package com.Grupo15.BolsaDeTrabajo.Features.Interview.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Interview.InterviewStatus;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.Type;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public class InterviewResponseDTO {
    private UUID externalId;
    private String candidateName;       // viene de application.candidate.user.name
    private String candidateLastName;   // viene de application.candidate.user.lastName
    private String offerTitle;          // viene de application.offer.title
    private LocalDateTime date;
    private Type type;
    private String linkMeeting;
    //private String feedbackCandidate;
    private InterviewStatus status;
}
