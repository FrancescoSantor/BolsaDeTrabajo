package com.Grupo15.BolsaDeTrabajo.Features.Comments;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;
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
@PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
@RequestMapping("/comments")
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping
    public ResponseEntity<CommentsResponseDTO> createComment(@Valid @RequestBody CommentsNewDTO newDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentsService.createComment(newDTO)); // Retorna 201 Created
    }

    // 2. EDITAR COMENTARIO
    // Usamos @PatchMapping ya que modificamos únicamente el campo parcial 'content'
    @PatchMapping("/{comment_externalId}")
    public ResponseEntity<CommentsResponseDTO> updateComment(
            @PathVariable UUID comment_externalId,
            @RequestParam String content) {
        return ResponseEntity.status(HttpStatus.OK).body(commentsService.updateComment(comment_externalId,content)); // Retorna 200 OK con el DTO actualizado
    }

    @DeleteMapping("/{comment_externalId}")
    public ResponseEntity<Void> deleteComment(@PathVariable UUID comment_externalId) {
        commentsService.DeleteComent(comment_externalId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/post/{postExternalId}")
    public ResponseEntity<List<CommentsResponseDTO>> listCommentsByPost(@PathVariable UUID postExternalId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentsService.ListCommentsByPost(postExternalId)); // Retorna 200 OK con la lista cronológica activa
    }
}
