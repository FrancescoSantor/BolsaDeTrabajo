package com.Grupo15.BolsaDeTrabajo.Features.Interview.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Interview.InterviewEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationsEntity;
import org.mapstruct.Mapper;

@Mapper
public interface InterviewMapper {

    InterviewResponseDTO toResponse(InterviewEntity interview);

    InterviewEntity toEntity(InterviewRequestDTO request, PostulationsEntity application);
}
