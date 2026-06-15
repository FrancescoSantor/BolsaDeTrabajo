package com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Title;
import lombok.Builder;

@Builder
public record CandidatesRequestDTO(
        Long userId,
        String name,
        String lastName,
        String email,
        String password,
        String username,
        Title professionalTitle,
        String summary,
        String cvUrl,
        String linkedinUrl,
        String photoUrl
) {}