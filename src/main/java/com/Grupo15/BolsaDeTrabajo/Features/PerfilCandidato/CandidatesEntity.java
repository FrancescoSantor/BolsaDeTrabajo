package com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato;

import com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad.CandidateAbilityEntity;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.LaboralExperienceEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Guardados.SavedEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.*;

@Entity
@Table(name = "perfil_candidato")@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidatesEntity extends UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ENUM DE TITULO(EJ INGENIERO TECNICO LICENCIADO)
    @Enumerated(EnumType.STRING)
    private Title professionalTitle;

    @Column(columnDefinition = "TEXT")
    //resumen
    private String summary;


    private String cvUrl;
    private String linkedinUrl;
    private String photoUrl;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "candidate")
    private List<PostulationsEntity> applications;

    //relacion de candidatoHabilidad
    @OneToMany(mappedBy = "candidate")
    private List<CandidateAbilityEntity> abilityCandidates;

    @OneToMany(mappedBy = "candidate")
    private List<LaboralExperienceEntity> laboralExperiences;

    @OneToMany(mappedBy = "candidate")
    private List<SavedEntity> saved;
}


