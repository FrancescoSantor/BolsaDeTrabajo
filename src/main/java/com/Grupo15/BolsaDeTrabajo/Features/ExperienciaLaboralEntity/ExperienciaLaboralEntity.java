package com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;



public class ExperienciaLaboralEntity {

    @Entity
    @Table(name = "experiencia_laboral")
    @Data
    @NoArgsConstructor
    public class ExperienciaLaboral {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "candidato_id")
        private CandidatosEntity.PerfilCandidato candidato;

        private String empresa;
        private String cargo;

        private Date fechaInicio;
        private Date fechaFin;

        private boolean trabajoActual;

        @Column(columnDefinition = "TEXT")
        private String descripcion;

        @ManyToOne
        @JoinColumn(name = "id_empresa")
        private EmpresasEntity empresaRef;
    }



}
