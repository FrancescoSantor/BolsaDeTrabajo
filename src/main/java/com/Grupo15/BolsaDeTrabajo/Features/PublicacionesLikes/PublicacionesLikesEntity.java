package com.Grupo15.BolsaDeTrabajo.Features.PublicacionesLikes;

import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.PublicacionesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class PublicacionesLikesEntity {

    @Entity
    @Table(name = "publicacion_likes")
    @Data
    @NoArgsConstructor
    public class PublicacionLike {

        @EmbeddedId
        private PublicacionLikeId id;

        @ManyToOne
        @MapsId("usuarioId")
        private UsersEntity usuario;

        @ManyToOne
        @MapsId("publicacionId")
        private PublicacionesEntity publicacion;

        private Timestamp createdAt;
    }

    @Embeddable
    @Data
    @NoArgsConstructor
    public class PublicacionLikeId implements Serializable {
        private Long usuarioId;
        private Long publicacionId;
    }

}
