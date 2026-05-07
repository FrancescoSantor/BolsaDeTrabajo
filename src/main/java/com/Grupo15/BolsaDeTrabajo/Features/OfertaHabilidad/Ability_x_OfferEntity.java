package com.Grupo15.BolsaDeTrabajo.Features.OfertaHabilidad;

import com.Grupo15.BolsaDeTrabajo.Features.Commons.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Habilidad.AbilityEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfferEntity;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "oferta_habilidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ability_x_OfferEntity extends BaseEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        //Solucion de la tabla ofertas

        @ManyToOne
        @JoinColumn(name = "oferta_id")     // dudas preguntar profe
        private OfferEntity offer;

        //Solucion de la tabla habilidades

        @ManyToOne
        @JoinColumn(name = "habilidad_id")     // dudas preguntar profe
        private AbilityEntity abilities;

        private boolean required;
    }




