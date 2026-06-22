package com.Grupo15.BolsaDeTrabajo.Features.PostLikes;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Post.PostsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;

import lombok.*;

@Entity
@Table(name = "publicacion_likes",
        /*Aca evitamos que un mismo usuario le de me gusta multiples veces a la misma publicaciones   */
       uniqueConstraints = @UniqueConstraint(columnNames = {"usuario_id", "publicaciones_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostLikesEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity user;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private PostsEntity post;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt; //No se usa @prePersist para evitar conflictos, la fecha se setea manualmente en el service al hacer el insert
}