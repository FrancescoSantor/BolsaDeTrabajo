package com.Grupo15.BolsaDeTrabajo.Features.Mensajes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepository extends JpaRepository<MessagesEntity,Long> {
}
