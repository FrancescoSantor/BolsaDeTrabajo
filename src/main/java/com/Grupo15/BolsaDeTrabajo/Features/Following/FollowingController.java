package com.Grupo15.BolsaDeTrabajo.Features.Following;

import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Following.dto.FollowingsRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/following")
public class FollowingController {
    private final FollowingService followingService;

    @PostMapping("/company")
    public ResponseEntity<FollowingResponseDTO> followCompany(@RequestBody FollowingsRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(followingService.setFollowToCompany(requestDTO));
    }

    @PatchMapping("/company/{companyId}/unfollow")
    public ResponseEntity<FollowingResponseDTO> unfollowCompany(@PathVariable Long companyId) {
        return ResponseEntity.ok(followingService.unfollowCompany(companyId));
    }

    @PostMapping("/candidate")
    public ResponseEntity<FollowingResponseDTO> followCandidate(@RequestBody FollowingsRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(followingService.setFollowToCandidate(requestDTO));
    }

    @PatchMapping("/candidate/{candidateId}/unfollow")
    public ResponseEntity<FollowingResponseDTO> unfollowCandidate (@PathVariable Long candidateId) {
        return ResponseEntity.ok(followingService.unfollowCandidate(candidateId));
    }
}
