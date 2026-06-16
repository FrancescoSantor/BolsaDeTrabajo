package com.Grupo15.BolsaDeTrabajo.Features.PostLikes;

import com.Grupo15.BolsaDeTrabajo.Features.Post.PostsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikesEntity,Long> {
    boolean existsByCompanyIdAndOfferId(Long companyId, Long offerId);

    Optional<PostsEntity> findByExternalId (UUID externalId);
}
