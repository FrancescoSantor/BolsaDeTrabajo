package com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato;

import com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad.CandidateAbilityEntity;
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
public class CandidatesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity user;

    //ENUM DE TITULO(EJ INGENIERO TECNICO LICENCIADO)
    @Enumerated(EnumType.STRING)
    private Title professionalTitle;

    @Column(columnDefinition = "TEXT")
    private String summary;

    private String cvUrl;
    private String linkedinUrl;
    private String photoUrl;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "candidato")
    private List<PostulationsEntity> applications;

    //relacion de candidatoHabilidad
    @OneToMany(mappedBy = "candidato")
    private List<CandidateAbilityEntity> abilityCandidates;

    @OneToMany(mappedBy = "candidato")
    private List<LaboralExperienceEntity> laboralExperiences;

    @OneToMany(mappedBy = "candidato")
    private List<SavedEntity> saved;
}


