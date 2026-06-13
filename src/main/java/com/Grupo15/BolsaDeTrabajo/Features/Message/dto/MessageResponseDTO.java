package com.Grupo15.BolsaDeTrabajo.Features.Message.dto;
import java.sql.Timestamp;
import java.util.UUID;


public record MessageResponseDTO(
     UUID externalId,
     String issuerName,
     String issuerLastName,
     String receptorName,
     String receptorLastName,
     String content,
     boolean read,
     Timestamp createdAt){}
