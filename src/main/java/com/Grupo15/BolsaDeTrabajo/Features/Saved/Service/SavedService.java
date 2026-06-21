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
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.dto.SavedResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor

public class SavedService {

    private final SavedRepository savedRepository;
    private final CandidateRepository candidateRepository;
    private final OfferRepository offerRepository;
    private final CompanyRepository companyRepository;

    @Transactional
    public SavedResponseDTO createSaved(SavedRequestDTO savedRequestDTO) {

        if (savedRepository.existsByCandidateIdAndOfferId(savedRequestDTO.candidateId(), savedRequestDTO.offerId())) {
            throw new BussinesRulesException("This job offer has already been saved by the candidate.");
        }

        CandidatesEntity candidate = candidateRepository.findById(savedRequestDTO.candidateId())
                .orElseThrow(() -> new ElementNotFoundException("Candidate not found with ID: " + savedRequestDTO.candidateId()));

        OfferEntity offer = offerRepository.findById(savedRequestDTO.offerId())
                .orElseThrow(() -> new ElementNotFoundException("Job offer not found with ID: " + savedRequestDTO.offerId()));

        SavedEntity savedEntity = SavedEntity.builder()
                .candidate(candidate)
                .offer(offer)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        SavedEntity saved = savedRepository.save(savedEntity);

        return SavedMapper.toDto(saved);
    }

    @Transactional
    public SavedResponseDTO saveCandidate(Long companyId, UUID candidateId) {

        CompaniesEntity company = companyRepository.findById(companyId)
                .orElseThrow(() -> new ElementNotFoundException("Company not found with ID: " + companyId));

        CandidatesEntity candidate = candidateRepository.findById(candidateId)
                .orElseThrow(() -> new ElementNotFoundException("Candidate not found with ID: " + candidateId));

        if (savedRepository.existsByCompanyIdAndCandidateId(companyId, candidateId)) {
            throw new BussinesRulesException("This candidate has already been saved.");
        }
        SavedEntity savedEntity = SavedEntity.builder()
                .company(company)
                .candidate(candidate)
                .createdAt(Timestamp.from(Instant.now()))
                .build();

        SavedEntity saved = savedRepository.save(savedEntity);

        return SavedMapper.toDto(saved);
    }
}
