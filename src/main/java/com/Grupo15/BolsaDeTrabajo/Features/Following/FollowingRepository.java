package com.Grupo15.BolsaDeTrabajo.Features.Following;

import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowingRepository extends JpaRepository<FollowingsEntity,Long> {
    Optional<FollowingsEntity> findByFollowed(UsersEntity followedId);


    boolean existsByFollowerAndFollowed (UsersEntity followerId, UsersEntity followedId);

    List<FollowingsEntity> findAllByFollowedId (UsersEntity user);

    List<FollowingsEntity> findAllByFollower (UsersEntity user);

    List<FollowingsEntity> findAllByFollowedAndState(UsersEntity followed, FollowState state);
}
