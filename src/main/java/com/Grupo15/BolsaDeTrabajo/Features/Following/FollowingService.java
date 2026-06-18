package com.Grupo15.BolsaDeTrabajo.Features.Following;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingsRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompanyRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UserRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FollowingService {
    private final FollowingRepository followingRepository;
    private final FollowingMapper followingMapper;

    private final UserRepository userRepository;

    public FollowingResponseDTO setFollow(FollowingsRequestDTO requestDTO) {
        UsersEntity follower = userRepository.findByExternalId(requestDTO.followerId())
                .orElseThrow(() -> new ElementNotFoundException("The User has not been found."));

        UsersEntity followed = userRepository.findByExternalId(requestDTO.followedId())
                .orElseThrow(() -> new ElementNotFoundException("The User has not been found."));

        if(follower.getExternalId().equals(followed.getExternalId())) {
            throw new RuntimeException("You can't follow yourself."); //BusinessRuleExcepcion
        }

        if(followingRepository.existsByExternalFollowerIdAndExternalFollowedId(requestDTO.followerId(), requestDTO.followedId())) {
            throw new RuntimeException("You have already follow this User."); //BusinessRuleExcepcion
        }

        FollowingsEntity following = followingMapper.toEntity(requestDTO);
        following.setFollower(follower);
        following.setFollowed(followed);
        following.setState(FollowState.FOLLOWING);
        following.setCreatedAt(LocalDateTime.now());

        return followingMapper.toDto(followingRepository.save(following));
    }

    public FollowingResponseDTO unfollow (UUID userFollowedId) {
        UsersEntity followed = userRepository.findByExternalId(userFollowedId)
                .orElseThrow(() -> new ElementNotFoundException("The User doesn´t exists."));

        FollowingsEntity following = followingRepository.findByFollowedId(userFollowedId)
                        .orElseThrow(() -> new ElementNotFoundException("You didn´t follow yet."));

        if(following.getState() == FollowState.NOT_FOLLOWING) {
            throw new RuntimeException("You can't unfollow a User that you has not following."); //BusinessRuleExcepcion
        }
        following.setState(FollowState.NOT_FOLLOWING);

        return followingMapper.toDto(followingRepository.save(following));
    }

    public List<FollowingResponseDTO> getFollowers (UUID userId) {
        UsersEntity user = userRepository.findByExternalId(userId)
                .orElseThrow(() -> new ElementNotFoundException("The User has not been found."));

        return followingRepository.findAllByFollowedId(user)
                .stream()
                .map(followingMapper::toDto)
                .toList();
    }

    public List<FollowingResponseDTO> getFollowed (UUID userId) {
        UsersEntity user = userRepository.findByExternalId(userId)
                .orElseThrow(() -> new ElementNotFoundException("The User has not been found."));

        return followingRepository.findAllByFollower(user)
                .stream()
                .map(followingMapper::toDto)
                .toList();
    }
}
