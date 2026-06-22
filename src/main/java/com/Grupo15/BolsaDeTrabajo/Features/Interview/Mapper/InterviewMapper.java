package com.Grupo15.BolsaDeTrabajo.Features.Interview.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Interview.InterviewEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationsEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InterviewMapper {

    @Mapping(target = "candidateName", source = "application.candidate.name")
    @Mapping(target = "candidateLastName", source = "application.candidate.lastName")
    @Mapping(target = "offerTitle", source = "application.offer.title")
    InterviewResponseDTO toResponse(InterviewEntity interview);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "application", ignore = true)
    @Mapping(target = "feedbackCandidate", ignore = true)
    InterviewEntity toEntity(InterviewRequestDTO request, PostulationsEntity application);
}
