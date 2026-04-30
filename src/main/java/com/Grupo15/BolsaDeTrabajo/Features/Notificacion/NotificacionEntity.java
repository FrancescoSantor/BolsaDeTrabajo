package com.Grupo15.BolsaDeTrabajo.Features.Notificacion;

import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class NotificacionEntity {

    @Entity
    @Table(name = "notificacion")
    @Data
    @NoArgsConstructor
    public class Notificacion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private UsersEntity usuario;

        private String tipo;
        private String mensaje;
        private boolean leida;

        private Timestamp createdAt;
    }

}
