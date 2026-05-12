package com.Grupo15.BolsaDeTrabajo.Features.Users;

import com.Grupo15.BolsaDeTrabajo.Features.Comentarios.CommentsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Mensajes.MessagesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Notificacion.NotificationEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PublicacionesLikes.PostLikesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Seguimientos.FollowingsEntity;
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
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UsersEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(unique = true,nullable = false)
    private String password;

    private boolean active;

    //SOLUCION DE RELACION ROL/USUARIO
    @ManyToOne
    @JoinColumn(name = "rol_id")
    private RolesEntity rol;

    private Timestamp createdAt;

    @OneToMany(mappedBy = "usuario")
    private List<NotificationEntity> notifications;

    @OneToMany(mappedBy = "emisor")
    private List<MessagesEntity> issued_messages; //mensajes emitidos

    @OneToMany(mappedBy = "receptor")
    private List<MessagesEntity> received_messages;

    @OneToMany(mappedBy = "usuario")
    private List<PostLikesEntity> likes;

    @OneToMany(mappedBy = "usuario")
    private List<CommentsEntity> comments;

    @OneToMany(mappedBy = "usuario")
    private List<FollowingsEntity> followings;


}


