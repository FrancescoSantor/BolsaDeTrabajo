package com.Grupo15.BolsaDeTrabajo.Features.Interview.Controller;

import com.Grupo15.BolsaDeTrabajo.Features.Interview.InterviewStatus;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.Service.InterviewService;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewResponseDTO;
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

@RestController
@RequestMapping("/BolsaDeTrabajo/interviews")
@RequiredArgsConstructor
@Tag(name = "Interviews", description = "Endpoints for scheduling, updating, and managing job interview processes")
public class InterviewController {

    private final InterviewService interviewService;

    @PostMapping
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Schedule a new interview", description = "Allows a company to schedule an interview for an active job postulation.")
    @ApiResponse(responseCode = "201", description = "Interview scheduled successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request body payload configuration")
    @ApiResponse(responseCode = "403", description = "Access denied. Company role required")
    @ApiResponse(responseCode = "404", description = "Target job postulation application not found")
    @ApiResponse(responseCode = "412", description = "Precondition failed. The target postulation already has an interview assigned")
    public ResponseEntity<InterviewResponseDTO> createInterview(
            @Valid @RequestBody InterviewRequestDTO request) {
        InterviewResponseDTO response = interviewService.createInterview(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    @Operation(summary = "Get interview by ID", description = "Fetches the full scheduling and status details of a specific interview using its technical database identifier.")
    @ApiResponse(responseCode = "200", description = "Interview details retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Interview entity not found")
    public ResponseEntity<InterviewResponseDTO> getInterviewById(
            @Parameter(description = "Database Long primary key ID of the interview") @PathVariable Long id) {
        InterviewResponseDTO response = interviewService.getInterviewById(id);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{id}/status")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Update interview status state", description = "Allows companies to progress or modify an interview lifecycle stage status (e.g., PENDING, ACCEPTED, REJECTED).")
    @ApiResponse(responseCode = "200", description = "Interview status updated successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Restricted to Company role account owner")
    @ApiResponse(responseCode = "404", description = "Interview target entity not found")
    public ResponseEntity<InterviewResponseDTO> updateStatus(
            @Parameter(description = "Database Long primary key ID of the interview") @PathVariable Long id,
            @Parameter(description = "The new enum status state value to apply") @RequestParam InterviewStatus newStatus) {
        InterviewResponseDTO response = interviewService.updateStatus(id, newStatus);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('COMPANY')")
    @Operation(summary = "Delete an interview record", description = "Permanently removes an interview meeting log from the database based on its unique Long identifier.")
    @ApiResponse(responseCode = "204", description = "Interview record deleted successfully from database")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Interview reference not found")
    public ResponseEntity<Void> deleteInterview(
            @Parameter(description = "Database Long primary key ID of the interview to remove") @PathVariable Long id) {
        interviewService.deleteInterview(id);
        return ResponseEntity.noContent().build(); // Devuelve un 204 No Content
    }
}
