package com.Grupo15.BolsaDeTrabajo.Features.PostLikes;

import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto.PostLikesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto.PostLikesResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/BolsaDeTrabajo/posts/likes")
@PreAuthorize("hasRole('CANDIDATE')")
@RequiredArgsConstructor
@Tag(name = "Post Likes", description = "Endpoints for handling social interactions, allowing candidates to like or unlike company wall publications")
public class PostLikesController {

    private final PostLikeService postLikeService;

    @PostMapping("/toggle")
    @Operation(summary = "Toggle like on a post", description = "Switches the like status of a specific post for the authenticated candidate. If the post is not liked, it adds a like and increments the counter; if already liked, it removes it and decrements the counter.")
    @ApiResponse(responseCode = "200", description = "Like status toggled successfully. Returns the updated state flag and total likes count counter metrics")
    @ApiResponse(responseCode = "400", description = "Invalid request payload or target identity constraints missing")
    @ApiResponse(responseCode = "403", description = "Access denied. Action strictly restricted to Candidate role permissions context")
    @ApiResponse(responseCode = "404", description = "Target publication post or user context entity records not found")
    public ResponseEntity<PostLikesResponseDTO> toggleLike(@Valid @RequestBody PostLikesRequestDTO requestDto) {
        PostLikesResponseDTO response = postLikeService.toggleLike(requestDto);
        return ResponseEntity.ok(response);
    }
}