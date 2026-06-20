package com.Grupo15.BolsaDeTrabajo.Features.PostLikes;

import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto.PostLikesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto.PostLikesResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/posts/likes")
@RequiredArgsConstructor
public class PostLikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/toggle")
    public ResponseEntity<PostLikesResponseDTO> toggleLike(@Valid @RequestBody PostLikesRequestDTO requestDto) {
        PostLikesResponseDTO response = postLikeService.toggleLike(requestDto);
        return ResponseEntity.ok(response);
    }
}