package com.Grupo15.BolsaDeTrabajo.Features.Comments;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
@RequestMapping("/BolsaDeTrabajo/comments")
@Tag(name = "Comments", description = "Endpoints for creating, updating, deleting, and retrieving comments on publications")
public class CommentsController {

    private final CommentsService commentsService;

    @PostMapping
    @Operation(summary = "Create a new comment", description = "Allows an authenticated user (candidate or company) to publish a comment on an active post.")
    @ApiResponse(responseCode = "201", description = "Comment created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid payload or post/user is inactive")
    @ApiResponse(responseCode = "404", description = "Post or User entity not found")
    public ResponseEntity<CommentsResponseDTO> createComment(@Valid @RequestBody CommentsNewDTO newDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(commentsService.createComment(newDTO)); // Retorna 201 Created
    }

    @PatchMapping("/{commentExternalId}")
    @Operation(summary = "Update comment content", description = "Allows the original author to update the body content string of an active comment.")
    @ApiResponse(responseCode = "200", description = "Comment updated successfully")
    @ApiResponse(responseCode = "400", description = "Comment is inactive")
    @ApiResponse(responseCode = "403", description = "Access denied. Only the author can modify this comment")
    @ApiResponse(responseCode = "404", description = "Comment not found")
    public ResponseEntity<CommentsResponseDTO> updateComment(
            @Parameter(description = "Unique external UUID of the target comment") @PathVariable UUID commentExternalId,
            @Parameter(description = "New plain text content for the comment") @RequestParam String content,
            @AuthenticationPrincipal UserDetails userDetails) {
        return ResponseEntity.status(HttpStatus.OK).body(commentsService.updateComment(commentExternalId,content, userDetails.getUsername())); // Retorna 200 OK con el DTO actualizado
    }

    @DeleteMapping("/{commentExternalId}")
    @Operation(summary = "Delete a comment (Logical delete)", description = "Deactivates a specific comment. Can be executed by the author or an administrator user profile.")
    @ApiResponse(responseCode = "204", description = "Comment logically deleted successfully")
    @ApiResponse(responseCode = "400", description = "Comment is already inactive")
    @ApiResponse(responseCode = "403", description = "Access denied. Insufficient permissions")
    @ApiResponse(responseCode = "404", description = "Comment not found")
    public ResponseEntity<Void> deleteComment(
            @Parameter(description = "Unique external UUID of the comment to remove")@PathVariable UUID commentExternalId,
            @AuthenticationPrincipal UserDetails userDetails) {
        commentsService.DeleteComment(commentExternalId, userDetails.getUsername());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/post/{postExternalId}")
    @Operation(summary = "List comments belonging to a specific post", description = "Fetches a chronological list containing active comment DTO models mapped under a single post identifier.")
    @ApiResponse(responseCode = "200", description = "Comments history list retrieved successfully")
    public ResponseEntity<List<CommentsResponseDTO>> listCommentsByPost(
            @Parameter(description = "Unique external UUID of the parent publication post") @PathVariable UUID postExternalId) {
        return ResponseEntity.status(HttpStatus.OK).body(commentsService.ListCommentsByPost(postExternalId)); // Retorna 200 OK con la lista cronológica activa
    }
}
