package com.Grupo15.BolsaDeTrabajo.Features.Message;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.MessageEmptyException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.MessageNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.SelfMessagingException;
import com.Grupo15.BolsaDeTrabajo.Features.Message.Mapper.MessageMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UserRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class MessageService implements IMessageService{

    private final MessageRepository messageRepository;
    private final UserRepository usersRepository;
    private final MessageMapper messageMapper;
    private final CredentialsRepository credentialsRepository;

    @Transactional
    public MessageResponseDTO sendMessage(MessageRequestDTO request) {

        UsersEntity issuer = usersRepository.findByExternalId(request.issuerId())
                .orElseThrow(() ->
                        new ElementNotFoundException("Issuer not found"));

        UsersEntity receptor = usersRepository.findByExternalId(request.receptorId())
                .orElseThrow(() ->
                        new ElementNotFoundException("Receiver not found"));

       if (request.content() == null || request.content().isBlank()) {
            throw new MessageEmptyException("Message content cannot be empty");
        }

        if (request.issuerId().equals(request.receptorId())) {
            throw new SelfMessagingException(
                    "A user cannot send messages to himself");
        }

        MessageEntity message = new MessageEntity();

        message.setIssuer(issuer);
        message.setReceptor(receptor);
        message.setContent(request.content());
        message.setSeen(false);

        MessageEntity saved = messageRepository.save(message);

        return messageMapper.toDto(saved);
    }


    @Transactional
    public MessageResponseDTO markAsRead(UUID messageId) {

        MessageEntity message = messageRepository.findByExternalId(messageId)
                .orElseThrow(()
                        -> new ElementNotFoundException("Message not found"));

        message.setSeen(true);

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
                        new MessageNotFoundException("Message not found"));

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
                .findByReceptorExternalIdAndSeenFalse(receptorId)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }

    // no se si funcionara
    public List<MessageResponseDTO> getChat(UUID userA, UUID userB) {

        return Stream.concat(
                        messageRepository.findByIssuerExternalIdAndReceptorExternalId(userA, userB)
                                .stream(),

                        messageRepository.findByIssuerExternalIdAndReceptorExternalId(userB, userA)
                                .stream())
                .sorted(Comparator.comparing(MessageEntity::getCreatedAt))
                .map(messageMapper::toDto)
                .toList();
    }

    @Transactional
    public void deleteMessage(UUID messageId, String username) {

        MessageEntity message = messageRepository.findByExternalId(messageId)
                .orElseThrow(() ->
                        new MessageNotFoundException("Message not found"));

        CredentialsEntity credentials = credentialsRepository
                .findByUsername(username)
                .orElseThrow(() ->
                        new RuntimeException("Authenticated user not found"));

        UsersEntity loggedUser = credentials.getUsuario();

        if (!message.getIssuer().getId().equals(loggedUser.getId())) {
            throw new RuntimeException(
                    "You do not have permission to delete this message");
        }

        messageRepository.delete(message);
    }
}


