package com.Grupo15.BolsaDeTrabajo.Features.Notification;

import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationResponseDTO;
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
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<NotificationResponseDTO> receiveNotification(@Valid @RequestBody NotificationRequestDTO requestDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(notificationService.receiveNotification(requestDTO));
    }

    @PatchMapping("/{externalId}/read")
    public ResponseEntity<NotificationResponseDTO> readNotification(@PathVariable UUID externalId) {
        return ResponseEntity.ok(notificationService.readNotification(externalId));
    }
}
