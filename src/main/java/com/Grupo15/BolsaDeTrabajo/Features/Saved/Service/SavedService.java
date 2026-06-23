package com.Grupo15.BolsaDeTrabajo.Features.Saved.Service;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.BussinesRulesException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Company.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Company.CompanyRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.Mapper.SavedMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.SavedEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.SavedRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedOfferRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedOfferResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedCandidateRequestDto;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedCandidateResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class SavedService {

    private final SavedRepository savedRepository;
    private final CandidateRepository candidateRepository;
    private final OfferRepository offerRepository;
    private final CompanyRepository companyRepository;
    private final SavedMapper savedMapper;

    @Transactional
    public SavedOfferResponseDTO createSaved(SavedOfferRequestDTO savedOfferRequestDTO) {

        if (savedRepository.existsByCandidateExternalIdAndOfferExternalId(savedOfferRequestDTO.candidateExternalId(), savedOfferRequestDTO.offerExternalId())) {
            throw new BussinesRulesException("This job offer has already been saved by the candidate.");
        }

        CandidatesEntity candidate = candidateRepository.findByExternalId(savedOfferRequestDTO.candidateExternalId())
                .orElseThrow(() -> new ElementNotFoundException("Candidate not found with ID: " + savedOfferRequestDTO.candidateExternalId()));

        OfferEntity offer = offerRepository.findByExternalId(savedOfferRequestDTO.offerExternalId())
                .orElseThrow(() -> new ElementNotFoundException("Job offer not found with ID: " + savedOfferRequestDTO.offerExternalId()));

        SavedEntity savedEntity = SavedEntity.builder()
                .candidate(candidate)
                .offer(offer)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        SavedEntity saved = savedRepository.save(savedEntity);

        return savedMapper.toOfferDto(saved);
    }

    @Transactional
    public SavedCandidateResponseDTO saveCandidate(SavedCandidateRequestDto requestDto) {

        CompaniesEntity company = companyRepository.findByExternalId(requestDto.companyExternalId())
                .orElseThrow(() -> new ElementNotFoundException("Company not found with ID: " + requestDto.companyExternalId()));

        CandidatesEntity candidate = candidateRepository.findByExternalId(requestDto.candidateExternalId())
                .orElseThrow(() -> new ElementNotFoundException("Candidate not found with ID: " + requestDto.candidateExternalId()));

        if (savedRepository.existsByCompanyExternalIdAndCandidateExternalId(requestDto.companyExternalId(), requestDto.candidateExternalId())) {
            throw new BussinesRulesException("This candidate has already been saved.");
        }

        SavedEntity savedEntity = SavedEntity.builder()
                .company(company)
                .candidate(candidate)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        SavedEntity saved = savedRepository.save(savedEntity);

        return savedMapper.toCandidateDto(saved);
    }
}