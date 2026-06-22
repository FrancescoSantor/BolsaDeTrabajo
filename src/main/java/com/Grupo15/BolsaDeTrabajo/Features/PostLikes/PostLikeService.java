package com.Grupo15.BolsaDeTrabajo.Features.PostLikes;

import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto.PostLikesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto.PostLikesResponseDTO;


public interface PostLikeService {

    PostLikesResponseDTO toggleLike(PostLikesRequestDTO requestDto);
}
