package com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "experiencia_laboral")
public class ExperienciaLaboralEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private CandidatosEntity candidato;   // dudas

    //CONEXION CON EMPRESA??
    private String empresa;
    //ENUM DE CARGO
    private String cargo;

    private Date fechaInicio;
    private Date fechaFin;

    private boolean trabajoActual;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresasEntity empresaRef;  // dudas
}