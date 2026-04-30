package com.Grupo15.BolsaDeTrabajo.Features.Comentarios;

import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.PublicacionesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

public class ComentariosEntity {

    @Entity
    @Table(name = "publicacion_comentarios")
    @Data
    @NoArgsConstructor
    public class PublicacionComentario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private PublicacionesEntity publicacion;

        @ManyToOne
        private UsersEntity usuario;

        @Column(columnDefinition = "TEXT")
        private String contenido;

        private Timestamp createdAt;
    }

}
