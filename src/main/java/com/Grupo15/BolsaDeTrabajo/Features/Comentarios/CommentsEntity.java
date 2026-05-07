package com.Grupo15.BolsaDeTrabajo.Features.Comentarios;

import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.PostsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;

@Entity
@Table(name = "publicacion_comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private PostsEntity post;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity user;

    @Column(columnDefinition = "TEXT")
    private String content;

    private Timestamp createdAt;
}