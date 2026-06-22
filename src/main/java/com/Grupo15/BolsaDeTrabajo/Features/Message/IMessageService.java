package com.Grupo15.BolsaDeTrabajo.Features.Message;

import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageResponseDTO;

import java.util.List;
import java.util.UUID;

public interface IMessageService {

    MessageResponseDTO sendMessage(MessageRequestDTO request);
    MessageResponseDTO markAsRead(UUID messageId, String username);
    List<MessageResponseDTO> getSentMessages(UUID userId);
    List<MessageResponseDTO> getReceivedMessages(UUID userId, String username);
    List<MessageResponseDTO> searchMessagesByContent(UUID receptorId, String content, String username);
    List<MessageResponseDTO> getUnreadMessages(UUID receptorId, String username);
    List<MessageResponseDTO> getChat(UUID userA, UUID userB, String username);
    void deleteMessage(UUID messageId, String username);


}
