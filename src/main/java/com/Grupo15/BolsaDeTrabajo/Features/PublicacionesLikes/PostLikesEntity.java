package com.Grupo15.BolsaDeTrabajo.Features.PublicacionesLikes;

import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.PostsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;

import lombok.*;

@Entity
@Table(name = "publicacion_likes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private PostsEntity post;

    private Timestamp createdAt;
}