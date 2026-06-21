package com.Grupo15.BolsaDeTrabajo.Features.Post;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostsRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Posts", description = "Endpoints for company social updates, feed publications, images, and embedded user comments logs")
public class PostController {
    private final PostService postService;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Create a new social post", description = "Allows an authenticated company to publish text updates and media onto their public corporate profile wall.")
    @ApiResponse(responseCode = "201", description = "Post created successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request payload or required parameters missing")
    @ApiResponse(responseCode = "403", description = "Access denied. Company role authority required")
    public ResponseEntity<PostResponseDTO> create(@Valid @RequestBody PostsRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(postService.create(requestDTO));
    }

    @PatchMapping("/{postId}/update")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Partially update a post", description = "Allows a company to edit optional specific attributes of an active publication such as its title, description content, or attachment image URL.")
    @ApiResponse(responseCode = "200", description = "Post elements updated successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Target post entity log not found")
    public ResponseEntity<PostResponseDTO> updatePost(
            @Parameter(description = "Secure public UUID of the target post to edit") @PathVariable UUID postId,
            @Parameter(description = "New text title descriptor for the post") @RequestParam(required = false) String title,
            @Parameter(description = "Updated main text body description block") @RequestParam(required = false) String content,
            @Parameter(description = "New media image static hosting reference URL link") @RequestParam(required = false) String urlImage) {
        return ResponseEntity.ok(postService.updatePost(postId, title, content, urlImage));
    }

    @DeleteMapping("/{postId}/delete")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Delete a publication", description = "Performs a safe logical delete status transition cycle turning the post visibility reference to inactive.")
    @ApiResponse(responseCode = "200", description = "Post status altered to inactive successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Post context data records not found")
    public ResponseEntity<PostResponseDTO> deletePost(
            @Parameter(description = "Secure public UUID of the publication to drop") @PathVariable UUID postId) {
        return ResponseEntity.ok(postService.deletePost(postId));
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
    @Operation(summary = "Get single post by ID", description = "Fetches the full metadata metrics and structural parameters of an active post tracking entry via its unique public UUID.")
    @ApiResponse(responseCode = "200", description = "Post data payload details retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Active post entity profile not found with the provided identifier")
    public ResponseEntity<PostResponseDTO> getPost(
            @Parameter(description = "Secure public UUID of the targeted post") @PathVariable UUID postId) {
        return ResponseEntity.ok(postService.getPost(postId));
    }

    @GetMapping("/{companyId}")
    @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
    @Operation(summary = "List all posts from a specific company", description = "Retrieves the complete historical sequence list of public entries published exclusively under a unique company account UUID context.")
    @ApiResponse(responseCode = "200", description = "Company chronological post entries history list fetched successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Target company context logs not found")
    public ResponseEntity<List<PostResponseDTO>> getAllPostByCompany(
            @Parameter(description = "Unique secure public company ID identifier to query posts from") @PathVariable UUID companyId) {
        return ResponseEntity.ok(postService.getAllPostByCompany(companyId));
    }

    @GetMapping("/{postId}/comments")
    @PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
    @Operation(summary = "List all comments belonging to a post", description = "Fetches the complete sub-tree list log of user feedback replies and nested comments created inside a specific publication reference ID.")
    @ApiResponse(responseCode = "200", description = "Chronological active comments feedback logs list retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Target post profile context records not found")
    public ResponseEntity<List<CommentsResponseDTO>> getCommentsByPost(
            @Parameter(description = "Secure public UUID of the targeted parent post") @PathVariable UUID postId) {
        return ResponseEntity.ok(postService.getCommentsByPost(postId));
    }
}