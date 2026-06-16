package com.Grupo15.BolsaDeTrabajo.Features.Post;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsEntity;
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

    @PatchMapping("/{externalId}")
    public ResponseEntity<PostResponseDTO> updatePost( @PathVariable UUID externalId,
                                                       @RequestParam(required = false) String title,
                                                       @RequestParam(required = false) String content,
                                                       @RequestParam(required = false) String urlImage) {
        return ResponseEntity.ok(postService.updatePost(externalId, title, content, urlImage));
    }

    @DeleteMapping("/{externalId}")
    public ResponseEntity<PostResponseDTO> deletePost(@PathVariable UUID externalId) {
        return ResponseEntity.ok(postService.deletePost(externalId));
    }

//    @GetMapping("/{externalId}/comments")
//    public ResponseEntity<List<CommentsEntity>> viewCommentsPost(@PathVariable UUID externalId) {
//        return ResponseEntity.ok(postService.viewComentsPost(externalId));
//    }
}
