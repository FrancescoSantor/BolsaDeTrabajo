package com.Grupo15.BolsaDeTrabajo.Features.Message;

import com.Grupo15.BolsaDeTrabajo.Features.Message.Mapper.MessageMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UserRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final UserRepository usersRepository;
    private final MessageMapper messageMapper;

    @Transactional
    public MessageResponseDTO sendMessage(MessageRequestDTO request) {

        UsersEntity issuer = usersRepository.findByExternalId(request.issuerId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Issuer not found"));

        UsersEntity receptor = usersRepository.findByExternalId(request.receptorId())
                .orElseThrow(() ->
                        new EntityNotFoundException("Receiver not found"));

       /*if (request.content() == null || request.content().isBlank()) {
            throw new IllegalArgumentException("Message content cannot be empty");
        }*/ // aca iria mensajeNotFoundExc

        if (request.issuerId().equals(request.receptorId())) {
            throw new IllegalArgumentException(
                    "A user cannot send messages to himself");
        }

        MessageEntity message = new MessageEntity();

        message.setIssuer(issuer);
        message.setReceptor(receptor);
        message.setContent(request.content());
        message.setRead(false);

        MessageEntity saved = messageRepository.save(message);

        return messageMapper.toDto(saved);
    }


    @Transactional
    public MessageResponseDTO markAsRead(UUID messageId) {

        MessageEntity message = messageRepository.findByExternalId(messageId)
                .orElseThrow(()
                        -> new EntityNotFoundException("Message not found"));

        message.setRead(true);

        return messageMapper.toDto(messageRepository.save(message));
    }

    public List<MessageResponseDTO> getSentMessages(UUID userId) {

        return messageRepository.findByIssuerExternalId(userId)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }

    public List<MessageResponseDTO> getReceivedMessages(UUID userId) {

        return messageRepository.findByReceptorExternalId(userId)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }

    public MessageResponseDTO getMessageByExternalId(UUID externalId) {

        MessageEntity message = messageRepository.findByExternalId(externalId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Message not found"));

        return messageMapper.toDto(message);
    }

    public List<MessageResponseDTO> searchMessagesByContent(UUID receptorId, String content) {

        return messageRepository
                .findByReceptorExternalIdAndContentContainingIgnoreCase(
                        receptorId,
                        content)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }

    public List<MessageResponseDTO> getUnreadMessages(UUID receptorId) {

        return messageRepository
                .findByReceptorExternalIdAndReadFalse(receptorId)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }


}
