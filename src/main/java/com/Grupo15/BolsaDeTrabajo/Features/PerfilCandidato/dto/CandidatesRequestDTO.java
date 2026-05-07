package com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.dto;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.Title;

public record CandidatesRequestDTO(
        Long userId,
        Title professionalTitle,
        String summary,
        String cvUrl,
        String linkedinUrl,
        String photoUrl
) {}