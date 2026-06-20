package com.Grupo15.BolsaDeTrabajo.Features.Message;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("/{externalId}")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO getMessageById(@PathVariable UUID externalId) {

        return messageService.getMessageByExternalId(externalId);
    }

    @PatchMapping("/{externalId}/read")
    @ResponseStatus(HttpStatus.OK)
    public MessageResponseDTO markAsRead(@PathVariable UUID externalId) {

        return messageService.markAsRead(externalId);
    }

    @GetMapping("/received/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseDTO> getReceivedMessages(@PathVariable UUID userId) {

        return messageService.getReceivedMessages(userId);
    }

    @GetMapping("/unread/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseDTO> getUnreadMessages(@PathVariable UUID userId) {

        return messageService.getUnreadMessages(userId);
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseDTO> searchMessagesByContent(@RequestParam UUID receptorId,
                                                            @RequestParam String content) {

        return messageService.searchMessagesByContent(receptorId, content);
    }

    @GetMapping("/chat") // aca no se si poner los uuid pq quedaria un Url medio largo
    @ResponseStatus(HttpStatus.OK)
    public List<MessageResponseDTO> getChat(@RequestParam UUID userA,
                                            @RequestParam UUID userB) {

        return messageService.getChat(userA, userB);
    }
}