package com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Title;
import lombok.Builder;

import java.sql.Timestamp;

@Builder
public record CandidatesResponseDTO(
        Long id,
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