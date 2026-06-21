package com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Title;
import lombok.Builder;

import java.sql.Timestamp;
import java.util.UUID;

@Builder
public record CandidatesResponseDTO(
        UUID externalId,
        String name,
        String email,
        String lastName,
        boolean active,

        Title professionalTitle,
        String summary,
        String cvUrl,
        String linkedinUrl,
        String photoUrl,
        Timestamp updatedAt
) {}