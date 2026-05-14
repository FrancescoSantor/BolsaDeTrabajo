package com.Grupo15.BolsaDeTrabajo.Features.PublicacionesLikes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikesEntity,Long> {
}
