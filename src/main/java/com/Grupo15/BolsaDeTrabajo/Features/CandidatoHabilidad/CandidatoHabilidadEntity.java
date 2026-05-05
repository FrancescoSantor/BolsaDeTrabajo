package com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad;

import com.Grupo15.BolsaDeTrabajo.Features.Habilidad.HabilidadEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.Title;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
@Entity
@Table(name = "candidato_habilidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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


}

