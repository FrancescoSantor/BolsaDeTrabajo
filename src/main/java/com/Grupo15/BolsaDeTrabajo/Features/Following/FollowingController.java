package com.Grupo15.BolsaDeTrabajo.Features.Following;

import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingsRequestDTO;
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
@PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
@RequestMapping("/BolsaDeTrabajo/following")
@Tag(name = "Followings", description = "Endpoints for managing user connections, followers, and following relations between accounts")
public class FollowingController {
    private final FollowingService followingService;

    @PostMapping("/user")
    @Operation(summary = "Follow a user", description = "Establishes a new follow relation between a follower user and a target followed user.")
    @ApiResponse(responseCode = "201", description = "Successfully followed the user")
    @ApiResponse(responseCode = "400", description = "Invalid request payload, self-following attempt, or relationship already exists")
    @ApiResponse(responseCode = "404", description = "Follower or followed user not found")
    public ResponseEntity<FollowingResponseDTO> setFollow(@Valid @RequestBody FollowingsRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(followingService.setFollow(requestDTO));
    }

    @PatchMapping("/user/{userFollowedId}/unfollow")
    @Operation(summary = "Unfollow a user", description = "Updates an existing follow relationship state to stop following the designated user account.")
    @ApiResponse(responseCode = "200", description = "Successfully unfollowed the user")
    @ApiResponse(responseCode = "400", description = "Target user relationship is already in a non-following state")
    @ApiResponse(responseCode = "404", description = "User or active follow relation not found")
    public ResponseEntity<FollowingResponseDTO> unfollow(
            @Parameter(description = "Unique external UUID of the followed user to stop following") @PathVariable UUID userFollowedId) {
        return ResponseEntity.ok(followingService.unfollow(userFollowedId));
    }

    @GetMapping("/{userId}/followers")
    @Operation(summary = "Get user followers list", description = "Retrieves all active followers who are currently following the specified user account identifier.")
    @ApiResponse(responseCode = "200", description = "Followers list retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Target user account not found")
    public ResponseEntity<List<FollowingResponseDTO>> getFollowers(
            @Parameter(description = "Unique external UUID of the target user")@PathVariable UUID userId) {
        return ResponseEntity.ok(followingService.getFollowers(userId));
    }

    @GetMapping("/{userId}/followeds")
    @Operation(summary = "Get user following list", description = "Retrieves all user accounts that the specified user identifier is currently following.")
    @ApiResponse(responseCode = "200", description = "Following list retrieved successfully")
    @ApiResponse(responseCode = "404", description = "Target user account not found")
    public ResponseEntity<List<FollowingResponseDTO>> getFolloweds(
            @Parameter(description = "Unique external UUID of the target user") @PathVariable UUID userId) {
        return ResponseEntity.ok(followingService.getFollowings(userId));
    }
}
