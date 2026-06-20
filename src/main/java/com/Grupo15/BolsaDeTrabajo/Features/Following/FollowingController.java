package com.Grupo15.BolsaDeTrabajo.Features.Following;

import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingsRequestDTO;
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
@RequestMapping("/api/following")
public class FollowingController {
    private final FollowingService followingService;

    @PostMapping("/user")
    public ResponseEntity<FollowingResponseDTO> setFollow(@RequestBody FollowingsRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(followingService.setFollow(requestDTO));
    }

    @PatchMapping("/user/{userFollowedId}/unfollow")
    public ResponseEntity<FollowingResponseDTO> unfollow(@PathVariable UUID userFollowedId) {
        return ResponseEntity.ok(followingService.unfollow(userFollowedId));
    }

    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<FollowingResponseDTO>> getFollowers(@PathVariable UUID userId) {
        return ResponseEntity.ok(followingService.getFollowers(userId));
    }

    @GetMapping("/{userId}/followeds")
    public ResponseEntity<List<FollowingResponseDTO>> getFolloweds(@PathVariable UUID userId) {
        return ResponseEntity.ok(followingService.getFollowings(userId));
    }
}
