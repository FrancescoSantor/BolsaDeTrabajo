package com.Grupo15.BolsaDeTrabajo.Features.Comments;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Post.PostsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "publicacion_comentarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentsEntity extends BaseEntity {

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
    @Column(nullable = false)
    private boolean Active;

    @PrePersist
    protected void onCreate (){
        this.createdAt = Timestamp.from(Instant.now());
    }
}