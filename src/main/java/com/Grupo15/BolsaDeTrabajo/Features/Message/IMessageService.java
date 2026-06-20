package com.Grupo15.BolsaDeTrabajo.Features.Message;

import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IMessageService {

    MessageResponseDTO sendMessage(MessageRequestDTO request);
    MessageResponseDTO markAsRead(UUID messageId);
    List<MessageResponseDTO> getSentMessages(UUID userId);
    List<MessageResponseDTO> getReceivedMessages(UUID userId);
    MessageResponseDTO getMessageByExternalId(UUID externalId);
    List<MessageResponseDTO> searchMessagesByContent(UUID receptorId, String content);
    List<MessageResponseDTO> getUnreadMessages(UUID receptorId);
    List<MessageResponseDTO> getChat(UUID userA, UUID userB);




}
