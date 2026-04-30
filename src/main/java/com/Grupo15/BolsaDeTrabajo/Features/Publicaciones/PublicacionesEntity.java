package com.Grupo15.BolsaDeTrabajo.Features.Publicaciones;

import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaLaboralEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class PublicacionesEntity {

    @Entity
    @Table(name = "publicaciones")
    @Data
    @NoArgsConstructor
    public class Publicacion {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        private EmpresasEntity empresa;

        @ManyToOne
        private OfertaLaboralEntity oferta;

        private String tipo;
        private String titulo;

        @Column(columnDefinition = "TEXT")
        private String contenido;

        private String imagenUrl;

        private int totalLikes;
        private int totalComentarios;

        private boolean activa;

        private Timestamp createdAt;
        private Timestamp updatedAt;
    }

}
