package com.Grupo15.BolsaDeTrabajo.Features.Entrevista.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Entrevista.InterviewEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Entrevista.dto.InterviewRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Entrevista.dto.InterviewResponseDTO;

import java.time.LocalDateTime;

public class InterviewMapper {

    public static InterviewResponseDTO toResponse (
            InterviewEntity interview,
            String candidateName,
            String candidateLastName,
            String offerTitle,
            String offerLocation) {

        return InterviewResponseDTO.builder()
                .externalId(interview.getExternalId())
                .candidateName(candidateName)
                .candidateLastName(candidateLastName)
                .offerTitle(offerTitle)
                .offerLocation(offerLocation)
                .date(interview.getDate())
                .type(interview.getType())
                .linkMeeting(interview.getLinkMeeting())
                .feedbackCandidate(interview.getFeedbackCandidate())
                .status(interview.getStatus())
                .build();
    }

    public static InterviewEntity toRequest (InterviewRequestDTO request) {
        return InterviewEntity.builder()
                .id(request.applicationId())
                .date(request.date())
                .type(request.type())
                .linkMeeting(request.linkMeeting())
                .companyNotes(request.companyNotes())
                .build();
    }
}
