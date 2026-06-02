package com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Title;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.UUID;


@Builder
public class CandidatesResponseDTO {
    private UUID externalId;
    private String name;
    private String email;
    private Title professionalTitle;
    private String summary;
    private String cvUrl;
    //private String linkedinUrl;
    //private String photoUrl;
    //rivate Timestamp updatedAt;
}