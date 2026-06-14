package com.Grupo15.BolsaDeTrabajo.Features.PostLikes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikesEntity,Long> {
    // Busca si ya existe un registro exacto de ese usuario para ese post
    Optional<PostLikesEntity> findByUserIdAndPostId(Long userId, Long postId);
}
