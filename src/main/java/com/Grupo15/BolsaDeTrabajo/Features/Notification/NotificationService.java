package com.Grupo15.BolsaDeTrabajo.Features.Notification;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompanyRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UserRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class NotificationService {
    private final NotificationRepository notificationRepository;
    private final NotificationMapper notificationMapper;

    private final UserRepository userRepository;
    private final CandidateRepository candidateRepository;
    private final CompanyRepository companyRepository;

    public NotificationResponseDTO receiveNotification(NotificationRequestDTO requestDTO) {
        CandidatesEntity candidate = null;
        CompaniesEntity company = null;

        if (candidateRepository.existsById(requestDTO.userId())) {
            candidate = candidateRepository.findById(requestDTO.userId())
                    .orElseThrow(() -> new RuntimeException("The user has not been found."));
        } else {
            company = companyRepository.findById(requestDTO.userId()) // ← companyId correcto
                    .orElseThrow(() -> new RuntimeException("The company has not been found."));
        }

        NotificationEntity notification = notificationMapper.toEntity(requestDTO);

        if (requestDTO.message() == null || requestDTO.message().isBlank()) {
            throw new RuntimeException("The message is blank.");  //BusinessRuleException
        }

        notification.setMessage(requestDTO.message());
        notification.setRead(false);
        notification.setCreatedAt(Timestamp.from(Instant.now()));

        return notificationMapper.toDto(notificationRepository.save(notification));
    }

    public NotificationResponseDTO readNotification(UUID externalId) {
        NotificationEntity notification = notificationRepository.findByUUID(externalId)
                .orElseThrow(() -> new RuntimeException("The notification doesn´t exists.")); //ResourceNotFoundException

        notification.setRead(true);

        return notificationMapper.toDto(notificationRepository.save(notification));
    }
}
