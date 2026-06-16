package com.Grupo15.BolsaDeTrabajo.Features.Post;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PostRepository extends JpaRepository<PostsEntity,Long> {
    boolean existsByCompanyIdAndOfferId(Long companyId, Long offerId);

    Optional<PostsEntity> findByExternalId (UUID externalId);
}
