package com.Grupo15.BolsaDeTrabajo.Features.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostsEntity,Long> {
    boolean existsByExternalId(UUID postId);

    boolean existsByCompanyExternalIdAndOfferExternalId(UUID companyId, UUID offerId);

    List<PostsEntity> findAllByCompanyExternalId(UUID companyId);

    Optional<PostsEntity> findByExternalId (UUID externalId);
}
