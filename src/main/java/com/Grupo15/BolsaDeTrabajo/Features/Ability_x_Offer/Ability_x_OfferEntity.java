package com.Grupo15.BolsaDeTrabajo.Features.Ability_x_Offer;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Ability.AbilityEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
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
        @JoinColumn(name = "offer_id")     // dudas preguntar profe
        private OfferEntity offer;

        //Solucion de la tabla habilidades

        @ManyToOne
        @JoinColumn(name = "ability_id")     // dudas preguntar profe
        private AbilityEntity ability;

        private boolean required;
    }




