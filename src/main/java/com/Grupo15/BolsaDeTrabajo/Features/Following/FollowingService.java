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
        CandidatesEntity candidate = candidateRepository.findById(requestDTO.followerId())
                .orElseThrow(() -> new RuntimeException("The User has not been found.")); //ResourceNotFoundExcepcion

        CompaniesEntity company = companyRepository.findById(requestDTO.followedId())
                .orElseThrow(() -> new RuntimeException("The Company has not been found.")); //ResourceNotFoundExcepcion

        if(requestDTO.followerId().equals(requestDTO.followedId())) {
            throw new RuntimeException("You can't follow yourself."); //BusinessRuleExcepcion
        }

        if(followingRepository.existsByUserIdAndCompanyID(requestDTO.followerId(), requestDTO.followedId())) {
            throw new RuntimeException("You have already follow this Company."); //BusinessRuleExcepcion
        }

        FollowingsEntity following = followingMapper.toEntity(requestDTO);
        following.setFollower(candidate);
        following.setFollowed(company);
        following.setState(FollowState.FOLLOWING);
        following.setCreatedAt(LocalDateTime.now());

        return followingMapper.toDto(followingRepository.save(following));
    }

    public FollowingResponseDTO unfollowCompany (Long companyId) {
        if(!companyRepository.existsById(companyId)) {
            throw new RuntimeException("The Company doesn´t exists."); //ResourceNotFoundExcepcion
        }
        FollowingsEntity following = followingRepository.findByCompanyId(companyId)
                        .orElseThrow(() -> new RuntimeException("You didn´t follow yet.")); //ResourceNotFoundExcepcion

        if(following.getState() == FollowState.NOT_FOLLOWING) {
            throw new RuntimeException("You can't unfollow a Company that you has not following."); //BusinessRuleExcepcion
        }
        following.setState(FollowState.NOT_FOLLOWING);

        return followingMapper.toDto(followingRepository.save(following));
    }

    public FollowingResponseDTO setFollowToCandidate(FollowingsRequestDTO requestDTO) {
        CompaniesEntity company = companyRepository.findById(requestDTO.followerId())
                .orElseThrow(() -> new RuntimeException("The Company has not been found.")); //ResourceNotFoundExcepcion

        CandidatesEntity candidate = candidateRepository.findById(requestDTO.followedId())
                .orElseThrow(() -> new RuntimeException("The User has not been found.")); //ResourceNotFoundExcepcion

        if(requestDTO.followerId().equals(requestDTO.followedId())) {
            throw new RuntimeException("You can't follow yourself."); //BusinessRuleExcepcion
        }
        if(followingRepository.existsByUserIdAndCompanyID(requestDTO.followerId(), requestDTO.followedId())) {
            throw new RuntimeException("You have already follow this User."); //BusinessRuleExcepcion
        }

        FollowingsEntity following = followingMapper.toEntity(requestDTO);
        following.setFollower(company);
        following.setFollowed(candidate);
        following.setState(FollowState.FOLLOWING);
        following.setCreatedAt(LocalDateTime.now());

        return followingMapper.toDto(followingRepository.save(following));
    }

    public FollowingResponseDTO unfollowCandidate(Long candidateId) {
        if(!candidateRepository.existsById(candidateId)) {
            throw new RuntimeException("The User doesn´t exists."); //ResourceNotFoundExcepcion
        }
        FollowingsEntity following = followingRepository.findByUserId(candidateId)
                .orElseThrow(() -> new RuntimeException("You didn´t follow yet.")); //ResourceNotFoundExcepcion

        if(following.getState() == FollowState.NOT_FOLLOWING) {
            throw new RuntimeException("You can't unfollow a Company that you has not following."); //BusinessRuleExcepcion
        }
        following.setState(FollowState.NOT_FOLLOWING);

        return followingMapper.toDto(followingRepository.save(following));
    }
}
