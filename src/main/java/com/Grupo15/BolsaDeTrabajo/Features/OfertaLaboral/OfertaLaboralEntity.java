package com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulacionEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class OfertaLaboralEntity {

    @Entity
    @Table(name = "oferta_laboral")
    @Data
    @NoArgsConstructor
    public class OfertaLaboral {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "empresa_id")
        private EmpresasEntity empresa;

        private String titulo;

        @Column(columnDefinition = "TEXT")
        private String descripcion;

        private String ubicacion;
        private String modalidad;
        private String tipoContrato;

        private Double salarioMin;
        private Double salarioMax;

        private String estado;

        private Timestamp fechaPublicacion;
        private Timestamp fechaCierre;

        @OneToMany(mappedBy = "oferta")
        private List<PostulacionEntity> postulaciones;
    }

}
