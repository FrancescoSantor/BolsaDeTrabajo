package com.Grupo15.BolsaDeTrabajo.Features.Notificacion;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;

import lombok.*;

@Entity
@Table(name = "notificacion")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Agregado de relacion con usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity user;

    /*ENUM TIPO DE NOTIFICACION
    @Enumerated(EnumType.STRING)
    private String type;
    */
    private String message;
    private boolean read;

    private Timestamp createdAt;
}


