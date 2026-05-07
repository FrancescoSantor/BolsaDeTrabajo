package com.Grupo15.BolsaDeTrabajo.Features.Habilidad;

import com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad.CandidateAbilityEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Commons.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaHabilidad.Ability_x_OfferEntity;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;

@Entity
@Table(name = "habilidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AbilityEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String category;

    @OneToMany(mappedBy = "habilidades")
    private List<Ability_x_OfferEntity> ability_x_OfferEntities; // tiene sentido que desde aca se pueda llamar ??

    @OneToMany(mappedBy = "habilidad")
    private List<CandidateAbilityEntity> abilityCandidate;   //   tiene sentido que desde aca se pueda llamar ??


    }


