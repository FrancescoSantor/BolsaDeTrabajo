package com.Grupo15.BolsaDeTrabajo.Features.Message;

import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/BolsaDeTrabajo/messages")
@PreAuthorize("hasAnyRole ('COMPANY','CANDIDATE')")
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO sendMessage(@Valid @RequestBody MessageRequestDTO dto) {
        return messageService.sendMessage(dto);
    }

    @PatchMapping("/{externalId}/read")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO markAsRead(@PathVariable UUID externalId, Authentication authentication) {
        return messageService.markAsRead(externalId, authentication.getName());
    }

    @GetMapping("/received/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseDTO> getReceivedMessages(@PathVariable UUID userId, Authentication authentication) {
        return messageService.getReceivedMessages(userId, authentication.getName());
    }

    @GetMapping("/unread/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseDTO> getUnreadMessages(@PathVariable UUID userId, Authentication authentication) {
        return messageService.getUnreadMessages(userId, authentication.getName());
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseDTO> searchMessagesByContent(@RequestParam UUID receptorId,
                                                            @RequestParam String content,
                                                            Authentication authentication) {
        return messageService.searchMessagesByContent(receptorId, content, authentication.getName());
    }

    @GetMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseDTO> getChat(@RequestParam UUID userA,
                                            @RequestParam UUID userB,
                                            Authentication authentication) {
        return messageService.getChat(userA, userB, authentication.getName());
    }

    @DeleteMapping("/{messageId}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMessage(@PathVariable UUID messageId, Authentication authentication) {
        messageService.deleteMessage(messageId, authentication.getName());
    }
}