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
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        postulation.setActive(true);
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

    @Transactional
    public PostulationResponseDTO updateStatusPostulation(UUID externalId, PostulationState postulationState){

        PostulationsEntity postulation = postulationRepository.findByExternalId(externalId)
                .orElseThrow(/*REBOLEAS POSTULATION NOT FOUND EXCEPTION*/);

        if (postulation.getStatus() != PostulationState.WAITING){
            //no se permite modificar el estado de la postulacion EXCEPTION
        }

        if (postulation.getStatus() == postulationState){
            //REBOLEAS EXCEPCION DE QUE NO SE PUEDE SETTEAR UN ESTADO 2 VECES IGUALES
        }



        postulation.setStatus(postulationState);

        postulationRepository.save(postulation);

        return new PostulationResponseDTO(
                candidateMapper.toDto(postulation.getCandidate()),
                offerMapper.toDto(postulation.getOffer()),
                postulation.getStatus(),
                postulation.getCoverLetter(),
                postulation.getPostulationDate()
        );

    }

    public List<PostulationResponseDTO> findBy (UUID candidateId, UUID offerId, PostulationState state){
        return postulationRepository.findPostulationsWithFilters(candidateId,offerId,state)
                .stream()
                .map(postulation -> new PostulationResponseDTO(
                        candidateMapper.toDto(postulation.getCandidate()),
                        offerMapper.toDto(postulation.getOffer()),
                        postulation.getStatus(),
                        postulation.getCoverLetter(),
                        postulation.getPostulationDate()
                )).toList();

    }

    @Transactional
    public void Delete (UUID postulationId){

        PostulationsEntity postulation = postulationRepository.findByExternalId(postulationId)
                .orElseThrow(/*arrojas exception de not found*/);

        if (!postulation.isActive()){
            // tiras exception ya se dio de baja la postulacion
        }

        if (postulation.getStatus() != PostulationState.WAITING){
            //tiras excepcion de regla de negocio no se puede dar de baja una postulacion ya aceptada o rechazada
        }

        postulation.setActive(false);

        postulationRepository.save(postulation);
    }





}
