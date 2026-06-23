package com.Grupo15.BolsaDeTrabajo.Features.Notification.Sender;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.NotificationService;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.dto.NotificationRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationsEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class NotificationSender {
    private final NotificationService notificationService;
    private final OfferRepository offerRepository;
    private final PostulationRepository postulationRepository;


    public void sendNewApplicationNotification(UUID companyExternalId, String candidateName, UUID offerExternalId) {
        OfferEntity offer = offerRepository.findByExternalId(offerExternalId)
                .orElseThrow(() -> new ElementNotFoundException("The Offer has not been found."));

        String message = String.format("Candidate %s has applied for your job offer: %s", candidateName, offer.getTitle());

        notificationService.receiveNotification(new NotificationRequestDTO(companyExternalId, message));
    }

    public void sendApplicationStatusChangedNotification(UUID candidateExternalId, UUID offerExternalId, UUID postulationExternalId) {
        OfferEntity offer = offerRepository.findByExternalId(offerExternalId)
                .orElseThrow(() -> new ElementNotFoundException("The Offer has not been found."));

        PostulationsEntity postulation = postulationRepository.findByExternalId(postulationExternalId)
                .orElseThrow(() -> new ElementNotFoundException("The Postulation has not been found."));

        String message = String.format("Your application for '%s' has changed status to: %s", offer.getTitle(), postulation.getStatus());
        notificationService.receiveNotification(new NotificationRequestDTO(candidateExternalId, message));
    }
}