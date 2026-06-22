package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Mapper.CandidateMapper;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.BussinesRulesException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.NotDuplicatesException;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.Mapper.OfferMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferStatus;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO.PostulationNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO.PostulationResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsRepository;
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
    private final CredentialsRepository credentialsRepository;



    public PostulationResponseDTO CreatePostulation (PostulationNewDTO newDTO, String username){

        CredentialsEntity credentials = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("you don't have permissions to create this offer"));

        UsersEntity loggedUser = credentials.getUsuario();

        OfferEntity offer = offerRepository.findByExternalId(newDTO.idOffer())
                .orElseThrow(() ->new ElementNotFoundException("does not exists the offer that you want to postulate"));

        CandidatesEntity candidates = candidateRepository.findByExternalId(newDTO.idCandidate())
                .orElseThrow(() -> new ElementNotFoundException("does not exists the candidate profile whit this ID"));

        if (!loggedUser.getId().equals(candidates.getId())){
            throw new RuntimeException("you don't have the permission to postulate another candidate");
        }

        if(postulationRepository.existsByCandidateAndOffer(candidates,offer)){
            throw new NotDuplicatesException("the candidate can´t postulate more than 1 time for each offer");
        }

        if(offer.getOfferStatus() == OfferStatus.CLOSE){
            throw new BussinesRulesException("The offer that you want to postulate was closed");
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
    public PostulationResponseDTO updateStatusPostulation(UUID externalId, PostulationState postulationState, String username){

        PostulationsEntity postulation = postulationRepository.findByExternalId(externalId)
                .orElseThrow(()->new ElementNotFoundException("The postulation that you want to update does not exists"));

        CredentialsEntity credentials = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found"));

        UsersEntity loggedUser = credentials.getUsuario();

        if (!loggedUser.getId().equals(postulation.getOffer().getCompany().getId())){
            throw new RuntimeException("you don't haver permissions to update the status of the postulation");
        }

        if (postulation.getStatus() != PostulationState.WAITING){
            throw new BussinesRulesException("You can´t change the status of the offer when it is change already");
        }

        if (postulation.getStatus() == postulationState){
            throw new BussinesRulesException("The state was already change");
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
    public void Delete (UUID postulationId, String username){

        PostulationsEntity postulation = postulationRepository.findByExternalId(postulationId)
                .orElseThrow(() -> new ElementNotFoundException("The postulation that you wants to delete does not exists"));

        if (!postulation.isActive()){
            throw new BussinesRulesException("The postulation was already deleted");
        }

        CredentialsEntity credentials = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Authenticated user not found "));

        UsersEntity loggedUser = credentials.getUsuario();

        if (!postulation.getCandidate().getId().equals(loggedUser.getId())){
            throw new RuntimeException("you don't have permission to delete this postulation");
        }


        if (postulation.getStatus() != PostulationState.WAITING){
            throw new BussinesRulesException("you cant delete a postulation that have a state distinct of waiting");
        }

        postulation.setActive(false);

        postulationRepository.save(postulation);
    }





}
