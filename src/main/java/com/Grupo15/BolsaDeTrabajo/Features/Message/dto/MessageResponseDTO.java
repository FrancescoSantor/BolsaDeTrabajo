package com.Grupo15.BolsaDeTrabajo.Features.Message.dto;
import java.sql.Timestamp;
import java.util.UUID;


public record MessageResponseDTO(
     UUID externalId,
     String issuerName,
     String receptorName,
     String content,
     boolean read,
     Timestamp createdAt){}
