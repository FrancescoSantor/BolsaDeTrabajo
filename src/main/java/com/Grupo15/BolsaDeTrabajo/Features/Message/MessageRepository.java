package com.Grupo15.BolsaDeTrabajo.Features.Message;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {


    Optional<MessageEntity> findByExternalId(UUID externalId);

    List<MessageEntity> findByIssuerExternalId(UUID issuerId);

    List<MessageEntity> findByReceptorExternalId(UUID receptorId);

    List<MessageEntity> findByReceptorExternalIdAndReadFalse(UUID receptorId);

    //List<MessageEntity> findByContentContainingIgnoreCase (String content);

    List<MessageEntity> findByReceptorExternalIdAndContentContainingIgnoreCase(UUID receptorId, String content);

    List<MessageEntity> findByIssuerExternalIdAndReceptorExternalId(UUID issuerId, UUID receptorId);


}
