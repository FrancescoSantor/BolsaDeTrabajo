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

        MessageEntity message = new MessageEntity();

        message.setIssuer(issuer);
        message.setReceptor(receptor);
        message.setContent(request.content());
        message.setRead(false);

        MessageEntity saved = messageRepository.save(message);

        return messageMapper.toDto(saved);
    }


}
