package com.Grupo15.BolsaDeTrabajo.Features.Comentarios;

import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.PublicacionesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "publicacion_comentarios")
public class ComentariosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "publicacion_id")
    private PublicacionesEntity publicacion;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity usuario;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private Timestamp createdAt;
}