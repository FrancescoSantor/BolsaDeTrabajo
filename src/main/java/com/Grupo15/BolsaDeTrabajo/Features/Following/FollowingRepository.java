package com.Grupo15.BolsaDeTrabajo.Features.Following;

import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FollowingRepository extends JpaRepository<FollowingsEntity,Long> {
    Optional<FollowingsEntity> findByFollowedId (UUID followedId);

    Optional<FollowingsEntity> findByUserId (Long userId);

    boolean existsByExternalFollowerIdAndExternalFollowedId (UUID followerId, UUID followedId);

    List<FollowingsEntity> findAllByFollowedId (UsersEntity user);

    List<FollowingsEntity> findAllByFollower (UsersEntity user);

    List<FollowingsEntity> findAllByFollowedAndState(UsersEntity followed, FollowState state);
}
