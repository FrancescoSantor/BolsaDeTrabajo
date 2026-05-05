package com.Grupo15.BolsaDeTrabajo.Features.Users;

import com.Grupo15.BolsaDeTrabajo.Features.Comentarios.ComentariosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Mensajes.MensajesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Notificacion.NotificacionEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PublicacionesLikes.PublicacionesLikesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Seguimientos.SeguimientosEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true,nullable = false)
    private String password;

    private boolean activo;

    //SOLUCION DE RELACION ROL/USUARIO
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private RolesEntity rol;

    private Timestamp createdAt;

    @OneToMany(mappedBy = "usuario")
    private List<NotificacionEntity> notificaciones;

    @OneToMany(mappedBy = "emisor")
    private List<MensajesEntity> mensajes_Emitidos;

    @OneToMany(mappedBy = "receptor")
    private List<MensajesEntity> mensajes_Recibidos;

    @OneToMany(mappedBy = "usuario")
    private List<PublicacionesLikesEntity> Likes;

    @OneToMany(mappedBy = "usuario")
    private List<ComentariosEntity> comentarios;

    @OneToMany(mappedBy = "usuario")
    private List<SeguimientosEntity> seguimientos;
}
