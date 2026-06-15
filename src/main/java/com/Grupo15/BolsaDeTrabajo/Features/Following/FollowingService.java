package com.Grupo15.BolsaDeTrabajo.Features.Following;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingsRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FollowingService {
    private final FollowingRepository followingRepository;
    private final FollowingMapper followingMapper;

    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;

    public FollowingResponseDTO setFollowToCompany (FollowingsRequestDTO requestDTO) {
        CandidatesEntity candidate = candidateRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new RuntimeException("The User has not been found."));

        CompaniesEntity company = companyRepository.findById(requestDTO.companyId())
                .orElseThrow(() -> new RuntimeException("The Company has not been found."));

        if(followingRepository.existsByUserIdAndCompanyID(requestDTO.userId(), requestDTO.companyId())) {
            throw new RuntimeException("You have already follow this Company.");
        }

        FollowingsEntity following = followingMapper.toEntity(requestDTO);
        following.setUser(candidate);
        following.setCompany(company);
        following.setState(FollowState.FOLLOWING);
        following.setCreatedAt(LocalDateTime.now());

        return followingMapper.toDto(followingRepository.save(following));
    }

    public FollowingResponseDTO unFollowCompany (Long companyId) {
        if(!companyRepository.existsById(companyId)) {
            throw new RuntimeException("The Company doesn´t exists.");
        }
        FollowingsEntity following = followingRepository.findByCompanyId(companyId)
                        .orElseThrow(() -> new RuntimeException("You didn´t follow yet."));

        if(following.getState() == FollowState.NOT_FOLLOWING) {
            throw new RuntimeException("You can't unfollow a Company that you has not following.");
        }
        following.setState(FollowState.NOT_FOLLOWING);

        return followingMapper.toDto(followingRepository.save(following));
    }

    public FollowingResponseDTO setFollowToCandidate(FollowingsRequestDTO requestDTO) {
        CandidatesEntity candidate = candidateRepository.findById(requestDTO.userId())
                .orElseThrow(() -> new RuntimeException("The User has not been found."));

        CompaniesEntity company = companyRepository.findById(requestDTO.companyId())
                .orElseThrow(() -> new RuntimeException("The Company has not been found."));

        if(followingRepository.existsByUserIdAndCompanyID(requestDTO.userId(), requestDTO.companyId())) {
            throw new RuntimeException("You have already follow this User.");
        }

        FollowingsEntity following = followingMapper.toEntity(requestDTO);
        following.setUser(candidate);
        following.setCompany(company);
        following.setState(FollowState.FOLLOWING);
        following.setCreatedAt(LocalDateTime.now());

        return followingMapper.toDto(followingRepository.save(following));
    }

    public FollowingResponseDTO unFollowCandidate(Long candidateId) {
        if(!candidateRepository.existsById(candidateId)) {
            throw new RuntimeException("The User doesn´t exists.");
        }
        FollowingsEntity following = followingRepository.findByUserId(candidateId)
                .orElseThrow(() -> new RuntimeException("You didn´t follow yet."));

        if(following.getState() == FollowState.NOT_FOLLOWING) {
            throw new RuntimeException("You can't unfollow a Company that you has not following.");
        }
        following.setState(FollowState.NOT_FOLLOWING);

        return followingMapper.toDto(followingRepository.save(following));
    }
}
