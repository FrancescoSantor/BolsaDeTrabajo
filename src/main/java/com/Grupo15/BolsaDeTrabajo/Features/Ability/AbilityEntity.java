package com.Grupo15.BolsaDeTrabajo.Features.Ability;

import com.Grupo15.BolsaDeTrabajo.Features.CandidateAbility.CandidateAbilityEntity;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Ability_x_Offer.Ability_x_OfferEntity;
import jakarta.persistence.*;

import java.util.List;

import lombok.*;

@Entity
@Table(name = "habilidad")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AbilityEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Enumerated (EnumType.STRING)
    @Column (name = "ability_category")
    private AbilityCategory category;

    @OneToMany(mappedBy = "ability")
    private List<Ability_x_OfferEntity> ability_x_OfferEntities; // tiene sentido que desde aca se pueda llamar ??

    @OneToMany(mappedBy = "ability")
    private List<CandidateAbilityEntity> abilityCandidate;   //   tiene sentido que desde aca se pueda llamar ??


    }


