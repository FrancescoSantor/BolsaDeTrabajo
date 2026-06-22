package com.Grupo15.BolsaDeTrabajo.Features.Comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CommentsRepository extends JpaRepository<CommentsEntity,Long> {

    Optional<CommentsEntity> findByExternalId(UUID externalId);



    @Query("SELECT c FROM CommentsEntity c " +
    "WHERE c.post.externalId = :PostExternalId " +
    "AND c.Active = true " +
    "ORDER BY createdAt ASC")
    List<CommentsEntity> findByPostExternalId(@Param("PostExternalId") UUID postExternalId);


}
