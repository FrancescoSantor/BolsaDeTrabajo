package com.Grupo15.BolsaDeTrabajo.Features.Notification;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends JpaRepository<NotificationEntity,Long> {
    Optional<NotificationEntity> findByUUID (UUID externalId);
}
