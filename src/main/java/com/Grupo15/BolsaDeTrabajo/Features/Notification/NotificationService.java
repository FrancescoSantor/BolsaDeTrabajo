package com.Grupo15.BolsaDeTrabajo.Features.Notification;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UserRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;
    private final UserRepository userRepository;

    public NotificationResponseDTO receiveNotification(NotificationRequestDTO requestDTO) {

        UsersEntity user = userRepository.findByExternalId(requestDTO.userId())
                .orElseThrow(() -> new ElementNotFoundException("The user has not been found"));


        if (requestDTO.message() == null || requestDTO.message().isBlank()) {
            throw new RuntimeException("The message is blank.");  //BusinessRuleException
        }

        NotificationEntity notification = notificationMapper.toEntity(requestDTO);
        notification.setUser(user);
        notification.setIsRead(false);
        //notification.setCreatedAt(Timestamp.from(Instant.now()));
        // solucionado el created at con un pre persists

        return notificationMapper.toDto(notificationRepository.save(notification));
    }

    public NotificationResponseDTO readNotification(UUID externalId) {
        NotificationEntity notification = notificationRepository.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("The notification doesn´t exists.")); //ResourceNotFoundException

        notification.setIsRead(true);

        return notificationMapper.toDto(notificationRepository.save(notification));
    }
}
