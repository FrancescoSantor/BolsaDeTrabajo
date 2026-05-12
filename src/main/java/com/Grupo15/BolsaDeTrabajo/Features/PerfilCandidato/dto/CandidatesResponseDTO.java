package com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.Title;
import lombok.Data;
import java.sql.Timestamp;
import java.util.UUID;

@Data
public class CandidatesResponseDTO {
    private UUID externalId;
    private String name;
    private String lastName;
    private String email;
    private Title professionalTitle;
    private String summary;
    private String cvUrl;
    private String linkedinUrl;
    private String photoUrl;
    private Timestamp updatedAt;
}