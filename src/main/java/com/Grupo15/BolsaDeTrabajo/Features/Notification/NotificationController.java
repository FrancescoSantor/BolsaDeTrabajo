package com.Grupo15.BolsaDeTrabajo.Features.Notification;

import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('COMPANY', 'CANDIDATE')")
@RequestMapping("/BolsaDeTrabajo/notifications")
@Tag(name = "Notifications", description = "Endpoints for handling system alerts, push notifications, and update statuses for users")
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    @Operation(summary = "Create/Receive a new notification", description = "Dispatches and logs a new system alert notification targeted to a specific user account.")
    @ApiResponse(responseCode = "201", description = "Notification registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid request payload or notification body content text is blank")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Target user account not found")
    public ResponseEntity<NotificationResponseDTO> receiveNotification(@Valid @RequestBody NotificationRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.receiveNotification(requestDTO));
    }

    @PatchMapping("/{externalId}/read")
    @Operation(summary = "Mark notification as read", description = "Updates the read status flag property of a targeted alert notification to true.")
    @ApiResponse(responseCode = "200", description = "Notification marked as read successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "Target notification record not found")
    public ResponseEntity<NotificationResponseDTO> readNotification(
            @Parameter(description = "Secure public UUID identifier of the notification") @PathVariable UUID externalId) {
        return ResponseEntity.ok(notificationService.readNotification(externalId));
    }
}