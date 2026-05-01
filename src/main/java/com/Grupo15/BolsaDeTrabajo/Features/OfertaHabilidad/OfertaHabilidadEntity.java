package com.Grupo15.BolsaDeTrabajo.Features.OfertaHabilidad;

import com.Grupo15.BolsaDeTrabajo.Features.Habilidad.HabilidadEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaEntity;
import jakarta.persistence.*;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "oferta_habilidad")
public class OfertaHabilidadEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        //Solucion de la tabla ofertas

        @ManyToOne
        @JoinColumn(name = "oferta_id")
        private OfertaEntity oferta;

        //Solucion de la tabla habilidades

        @ManyToOne
        @JoinColumn(name = "habilidad_id")
        private HabilidadEntity habilidades;

        private boolean requerida;
    }




