package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.Entrevista.EntrevistaEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatosEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "postulacion")
public class PostulacionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //solucion de mapeado a CandidatosEntity
    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private CandidatosEntity candidato;

    //solucion de mapeado a OfertaEntity
    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private OfertaEntity oferta;

    //ENUM DE ESTADO DE LA OFERTA
    private String estado;

    @Column(columnDefinition = "TEXT")
    private String cartaPresentacion;

    private Timestamp fechaPostulacion;
    private Timestamp fechaActualizacion;


    @OneToOne(mappedBy = "postulacion")
    private EntrevistaEntity entrevista;
}


