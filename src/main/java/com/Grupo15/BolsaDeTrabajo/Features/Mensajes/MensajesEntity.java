package com.Grupo15.BolsaDeTrabajo.Features.Mensajes;

import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class MensajesEntity {

    @Entity
    @Table(name = "mensajes")
    @Data
    @NoArgsConstructor
    public class Mensaje {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private UsersEntity emisor;

        @ManyToOne
        private UsersEntity receptor;

        @Column(columnDefinition = "TEXT")
        private String contenido;

        private boolean leido;

        private Timestamp createdAt;
    }

}
