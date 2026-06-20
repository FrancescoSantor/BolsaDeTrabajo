package com.Grupo15.BolsaDeTrabajo.Features.Post;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostsRequestDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/BolsaDeTrabajo/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<PostResponseDTO> create(@Valid @RequestBody PostsRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.create(requestDTO));
    }

    @PatchMapping("/{postId}/update")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<PostResponseDTO> updatePost( @PathVariable UUID postId,
                                                       @RequestParam(required = false) String title,
                                                       @RequestParam(required = false) String content,
                                                       @RequestParam(required = false) String urlImage) {
        return ResponseEntity.ok(postService.updatePost(postId, title, content, urlImage));
    }

    @DeleteMapping("/{postId}/delete")
    @PreAuthorize("hasRole('COMPANY')")
    public ResponseEntity<PostResponseDTO> deletePost(@PathVariable UUID postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable UUID postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @GetMapping("/{companyId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
    public ResponseEntity<List<PostResponseDTO>> getAllPostByCompany(@PathVariable UUID companyId) {
        return ResponseEntity.ok(postService.getAllPostByCompany(companyId));
    }

    @GetMapping("/{postId}/comments")
    @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
    public ResponseEntity<List<CommentsResponseDTO>> getCommentsByPost(@PathVariable UUID postId) {
        return ResponseEntity.ok(postService.getCommentsByPost(postId));
    }
}
