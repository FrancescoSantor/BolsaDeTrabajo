package com.Grupo15.BolsaDeTrabajo.Features.Notificacion;

import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "notificacion")
public class NotificacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Agregado de relacion con usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity usuario;

    //ENUM TIPO DE NOTIFICACION
    private String tipo;
    private String mensaje;
    private boolean leida;

    private Timestamp createdAt;
}


