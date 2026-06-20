package com.Grupo15.BolsaDeTrabajo.Features.Interview.Controller;

import com.Grupo15.BolsaDeTrabajo.Features.Interview.InterviewStatus;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.Service.InterviewService;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/interviews")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    public ResponseEntity<InterviewResponseDTO> createInterview(@Valid @RequestBody InterviewRequestDTO request) {
        InterviewResponseDTO response = interviewService.createInterview(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InterviewResponseDTO> getInterviewById(@PathVariable Long id) {
        InterviewResponseDTO response = interviewService.getInterviewById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<InterviewResponseDTO> updateStatus(@PathVariable Long id, @RequestParam InterviewStatus newStatus) {
        InterviewResponseDTO response = interviewService.updateStatus(id, newStatus);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInterview(@PathVariable Long id) {
        interviewService.deleteInterview(id);
        return ResponseEntity.noContent().build(); // Devuelve un 204 No Content
    }
}
