package com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad;

import com.Grupo15.BolsaDeTrabajo.Features.Habilidad.HabilidadEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatosEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Entity
@Table(name = "candidato_habilidad")
public class CandidatoHabilidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //RELACION CON CANDIDATO
    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private CandidatosEntity candidato;


    //RELACION CON HABILIDAD
    @ManyToOne
    @JoinColumn(name = "habilidad_id")
    private HabilidadEntity habilidad;


    //ENUM DE NIVEL
    private String nivel;
}

