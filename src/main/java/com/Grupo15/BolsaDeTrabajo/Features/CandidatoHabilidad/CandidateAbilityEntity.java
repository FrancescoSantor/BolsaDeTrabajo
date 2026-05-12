package com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Habilidad.AbilityEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatesEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "candidato_habilidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidateAbilityEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //RELACION CON CANDIDATO
    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private CandidatesEntity candidate;


    //RELACION CON HABILIDAD
    @ManyToOne
    @JoinColumn(name = "habilidad_id")
    private AbilityEntity ability;


}

