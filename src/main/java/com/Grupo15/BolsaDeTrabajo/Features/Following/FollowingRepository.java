package com.Grupo15.BolsaDeTrabajo.Features.Following;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FollowingRepository extends JpaRepository<FollowingsEntity,Long> {
    Optional<FollowingsEntity> findByCompanyId (Long companyId);

    Optional<FollowingsEntity> findByUserId (Long userId);

    boolean existsByUserIdAndCompanyID (Long userId, Long companyId);
}
