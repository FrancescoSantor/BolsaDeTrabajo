package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Mapper.CandidateMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.Mapper.OfferMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferStatus;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO.PostulationNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO.PostulationResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostulationService {

    private final PostulationRepository postulationRepository;
    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;
    private final CandidateRepository candidateRepository;
    private final CandidateMapper candidateMapper;



    public PostulationResponseDTO CreatePostulation (PostulationNewDTO newDTO){

        OfferEntity offer = offerRepository.findByExternalId(newDTO.idOffer())
                .orElseThrow(/*REBOLEAS EXCEPTION DE NOT FOUND*/);

        CandidatesEntity candidates = candidateRepository.findByExternalId(newDTO.idCandidate())
                .orElseThrow(/*REBOLEAS EXCEPTION DE NOT FOUND*/);


        if(postulationRepository.existsByCandidateAndOffer(candidates,offer)){
            //TIRAS EXCEPCION DE REGLA DE NEGOCIO O DE NO SE PUEDE REGISTRAR 2 VECES A UNA MISMA OFERTA
        }

        if(offer.getOfferStatus() == OfferStatus.CLOSE){
            //TIRAS EXCEPCION DE OFERTA YA CERRADA
        }

        PostulationsEntity postulation = new PostulationsEntity();

        postulation.setCandidate(candidates);
        postulation.setOffer(offer);
        postulation.setStatus(PostulationState.WAITING);
        postulation.setCoverLetter(newDTO.coverLetter());

        PostulationsEntity savedPostulation = postulationRepository.save(postulation);

        PostulationResponseDTO responseDTO = new PostulationResponseDTO(
                candidateMapper.toDto(candidates),
                offerMapper.toDto(offer),
                savedPostulation.getStatus(),
                savedPostulation.getCoverLetter(),
                savedPostulation.getPostulationDate());

        return responseDTO;


    }





}
