package com.Grupo15.BolsaDeTrabajo.Features.PostLikes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostLikeRepository extends JpaRepository<PostLikesEntity,Long> {
}
