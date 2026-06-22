package com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Title;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferStatus;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferType;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationState;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.UUID;

public record PostulationResponseDTO(
        UUID externalId,
        //CANDIDATE
        CandidatesResponseDTO candidatesResponseDTO,
        //OFFER
        OfferResponseDTO offerResponseDTO,
        //Postulation
        PostulationState status,
        String coverLetter,
        Timestamp postulationDate

) {
}
