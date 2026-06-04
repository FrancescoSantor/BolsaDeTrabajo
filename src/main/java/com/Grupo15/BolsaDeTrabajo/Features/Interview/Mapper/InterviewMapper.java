package com.Grupo15.BolsaDeTrabajo.Features.Interview.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Interview.InterviewEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewResponseDTO;

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
                .date(interview.getDate())
                .type(interview.getType())
                .linkMeeting(interview.getLinkMeeting())
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
