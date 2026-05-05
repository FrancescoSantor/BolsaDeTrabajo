package com.Grupo15.BolsaDeTrabajo.Features.Publicaciones;

import com.Grupo15.BolsaDeTrabajo.Features.Comentarios.ComentariosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PublicacionesLikes.PublicacionesLikesEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.*;

@Entity
@Table(name = "publicaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PublicacionesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresasEntity empresa;

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private OfertaEntity oferta;


    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    private String imagenUrl;

    private int totalLikes;
    private int totalComentarios;

    private boolean activa;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "publicacion")
    private List<PublicacionesLikesEntity> likes;

    @OneToMany(mappedBy = "publicacion")
    private List<ComentariosEntity> comentarios;
}


