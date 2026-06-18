package com.Grupo15.BolsaDeTrabajo.Features.Post;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostsRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/post")
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<PostResponseDTO> create(@RequestBody PostsRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.create(requestDTO));
    }

    @PatchMapping("/{postId}/update")
    public ResponseEntity<PostResponseDTO> updatePost( @PathVariable UUID postId,
                                                       @RequestParam(required = false) String title,
                                                       @RequestParam(required = false) String content,
                                                       @RequestParam(required = false) String urlImage) {
        return ResponseEntity.ok(postService.updatePost(postId, title, content, urlImage));
    }

    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<PostResponseDTO> deletePost(@PathVariable UUID postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostResponseDTO> getPost(@PathVariable UUID postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<List<PostResponseDTO>> getAllPostByCompany(@PathVariable UUID companyId) {
        return ResponseEntity.ok(postService.getAllPostByCompany(companyId));
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentsResponseDTO>> getCommentsByPost(@PathVariable UUID postId) {
        return ResponseEntity.ok(postService.getCommentsByPost(postId));
    }
}
