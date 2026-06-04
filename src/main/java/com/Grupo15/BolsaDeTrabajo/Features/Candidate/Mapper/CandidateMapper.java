package com.Grupo15.BolsaDeTrabajo.Features.Candidate.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesResponseDTO;

public class CandidateMapper {

    public static CandidatesResponseDTO toDto(CandidatesEntity candidatesEntity) {
        return CandidatesResponseDTO.builder()
                .externalId(candidatesEntity.getExternalId())
                .name(candidatesEntity.getName())
                //.lastName(candidatesEntity.getLastName())
                .email(candidatesEntity.getEmail())
                .professionalTitle(candidatesEntity.getProfessionalTitle())
                .summary(candidatesEntity.getSummary())
                .cvUrl(candidatesEntity.getCvUrl())
                //.linkedinUrl(candidatesEntity.getLinkedinUrl())
                //.photoUrl(candidatesEntity.getPhotoUrl())
                //.updatedAt(candidatesEntity.getUpdatedAt())
                .build();
    }

        public static CandidatesEntity toEntity (CandidatesRequestDTO request){
        return CandidatesEntity.builder()
                .professionalTitle(request.professionalTitle())
                .summary(request.summary())
                .cvUrl(request.cvUrl())
                //.linkedinUrl(request.linkedinUrl())
                //.photoUrl(request.photoUrl())
                .build();
        }
}