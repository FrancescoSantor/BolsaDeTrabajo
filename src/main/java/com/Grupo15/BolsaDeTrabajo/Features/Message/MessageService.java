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
    public MessageResponseDTO markAsRead(UUID messageId, String username) {
        MessageEntity message = messageRepository.findByExternalId(messageId)
                .orElseThrow(() -> new ElementNotFoundException("Message not found"));

        CredentialsEntity credentials = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new ElementNotFoundException("Authenticated user not found"));
        UsersEntity loggedUser = credentials.getUsuario();

        if (!message.getReceptor().getExternalId().equals(loggedUser.getExternalId())) {
            throw new RuntimeException("You need to be receptorr for mark as read this message.");
        }

        message.setSeen(true);
        return messageMapper.toDto(messageRepository.save(message));
    }

    public List<MessageResponseDTO> getSentMessages(UUID userId) {

        return messageRepository.findByIssuerExternalId(userId)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }

    public List<MessageResponseDTO> getReceivedMessages(UUID userId, String username) {
        validateUser(userId, username);

        return messageRepository.findByReceptorExternalId(userId)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }


    public List<MessageResponseDTO> searchMessagesByContent(UUID receptorId, String content, String username) {
        validateUser(receptorId, username);

        return messageRepository
                .findByReceptorExternalIdAndContentContainingIgnoreCase(receptorId, content)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }

    public List<MessageResponseDTO> getUnreadMessages(UUID receptorId, String username) {
        validateUser(receptorId, username);

        return messageRepository
                .findByReceptorExternalIdAndSeenFalse(receptorId)
                .stream()
                .map(messageMapper::toDto)
                .toList();
    }

    // no se si funcionara. Antes era mas simple pero cualquier usuario podia vver conversaciones ajenas si conocia los id.
    @Transactional
    public List<MessageResponseDTO> getChat(UUID userA, UUID userB, String username) {

        CredentialsEntity credentials = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new ElementNotFoundException("Authenticated user not found"));

        UsersEntity loggedUser = credentials.getUsuario();
        UUID loggedUserId = loggedUser.getExternalId();

        if (!loggedUserId.equals(userA) && !loggedUserId.equals(userB)) {
            throw new RuntimeException("You do not have permission to view this conversation.");
        }

        List<MessageEntity> messagesFromAToB = messageRepository.findByIssuerExternalIdAndReceptorExternalId(userA, userB);
        List<MessageEntity> messagesFromBToA = messageRepository.findByIssuerExternalIdAndReceptorExternalId(userB, userA);

        List<MessageEntity> allMessages = Stream.concat(messagesFromAToB.stream(), messagesFromBToA.stream())
                .sorted(Comparator.comparing(MessageEntity::getCreatedAt))
                .toList();

        allMessages.stream()
                .filter(message -> message.getReceptor().getExternalId().equals(loggedUserId) && !message.isSeen())
                .forEach(message -> {
                    message.setSeen(true);
                    messageRepository.save(message);
                });
        return allMessages.stream()
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

    private void validateUser(UUID externalId, String username) {
        CredentialsEntity credentials = credentialsRepository.findByUsername(username)
                .orElseThrow(() -> new ElementNotFoundException("Authenticated user not found"));

        UsersEntity loggedUser = credentials.getUsuario();

        if (!loggedUser.getExternalId().equals(externalId)) {
            throw new RuntimeException("You do not have permission to access this resource.");
        }
    }
}


