package com.Grupo15.BolsaDeTrabajo.Features.Message;

import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Messages", description = "Endpoints for private messaging system, chat histories, and unread notification handling between users")
public class MessageController {

    private final MessageService messageService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Send a private message", description = "Allows an authenticated user to send a new text message to another system user profile.")
    @ApiResponse(responseCode = "201", description = "Message sent successfully")
    @ApiResponse(responseCode = "400", description = "Empty message content or self-messaging validation failure")
    @ApiResponse(responseCode = "404", description = "Issuer or receptor user entity not found")
    public MessageResponseDTO sendMessage(@Valid @RequestBody MessageRequestDTO dto) {
        return messageService.sendMessage(dto);
    }

    @PatchMapping("/{externalId}/read")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Mark a message as read", description = "Updates a specific message status state to seen. Action restricted exclusively to the original receptor.")
    @ApiResponse(responseCode = "200", description = "Message marked as read successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. User is not the receptor of this message")
    @ApiResponse(responseCode = "404", description = "Message or authenticated session user data not found")
    public MessageResponseDTO markAsRead(
            @Parameter(description = "Secure public UUID of the target message") @PathVariable UUID externalId,
            Authentication authentication) {
        return messageService.markAsRead(externalId, authentication.getName());
    }

    @GetMapping("/received/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get received messages history", description = "Retrieves a complete list of private messages received by the designated user UUID account.")
    @ApiResponse(responseCode = "200", description = "Received messages logs fetched successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Users can only fetch their own incoming messages inbox")
    @ApiResponse(responseCode = "404", description = "User profile context not found")
    public List<MessageResponseDTO> getReceivedMessages(
            @Parameter(description = "Unique external UUID of the target message receptor") @PathVariable UUID userId,
            Authentication authentication) {
        return messageService.getReceivedMessages(userId, authentication.getName());
    }

    @GetMapping("/unread/{userId}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get unread messages list", description = "Fetches a filtered list containing incoming messages that have not been marked as read yet for a specific user.")
    @ApiResponse(responseCode = "200", description = "Unread notifications list retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Token owner mismatch")
    @ApiResponse(responseCode = "404", description = "User reference context not found")
    public List<MessageResponseDTO> getUnreadMessages(
            @Parameter(description = "Unique external UUID of the target message receptor") @PathVariable UUID userId,
            Authentication authentication) {
        return messageService.getUnreadMessages(userId, authentication.getName());
    }

    @GetMapping("/search")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Search received messages by content matching", description = "Queries across an inbox content history filtering entries using a partial case-insensitive string match search term.")
    @ApiResponse(responseCode = "200", description = "Search query results retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied")
    @ApiResponse(responseCode = "404", description = "User account not found")
    public List<MessageResponseDTO> searchMessagesByContent(
            @Parameter(description = "Unique external UUID of the receptor user account") @RequestParam UUID receptorId,
            @Parameter(description = "The target partial text content term to lookup") @RequestParam String content,
            Authentication authentication) {
        return messageService.searchMessagesByContent(receptorId, content, authentication.getName());
    }

    @GetMapping("/chat")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get mutual conversation log (Chat)", description = "Fetches the chronologically sorted text exchange history log between two unique system user entities.")
    @ApiResponse(responseCode = "200", description = "Mutual chat history sequence retrieved successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Authenticated session user must be either participant User A or User B")
    @ApiResponse(responseCode = "404", description = "Authenticated profile logs not found")
    public List<MessageResponseDTO> getChat(
            @Parameter(description = "External UUID of the first chat participant") @RequestParam UUID userA,
            @Parameter(description = "External UUID of the second chat participant") @RequestParam UUID userB,
            Authentication authentication) {
        return messageService.getChat(userA, userB, authentication.getName());
    }

    @DeleteMapping("/{messageId}")
    @PreAuthorize("hasAnyRole('CANDIDATE', 'COMPANY')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(summary = "Delete an outcoming message", description = "Permanently drops a message from the persistence layers database. Action restricted solely to the original message issuer.")
    @ApiResponse(responseCode = "204", description = "Message physical record deleted successfully")
    @ApiResponse(responseCode = "403", description = "Access denied. Only the original issuer can drop this message record")
    @ApiResponse(responseCode = "404", description = "Target message context reference not found")
    public void deleteMessage(
            @Parameter(description = "Secure public UUID of the message record to delete") @PathVariable UUID messageId,
            Authentication authentication) {
        messageService.deleteMessage(messageId, authentication.getName());
    }
}