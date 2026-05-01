package com.Grupo15.BolsaDeTrabajo.Features.PublicacionesLikes;

import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.PublicacionesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "publicacion_likes")
public class PublicacionesLikesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity usuario;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private PublicacionesEntity publicacion;

    private Timestamp createdAt;
}