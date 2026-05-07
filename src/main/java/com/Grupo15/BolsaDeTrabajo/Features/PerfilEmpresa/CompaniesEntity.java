package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

import com.Grupo15.BolsaDeTrabajo.Features.Commons.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.LaboralExperienceEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.PostsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Seguimientos.FollowingsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "perfil_empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompaniesEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity user;

    //??
        private String registeredName;

    @Column(unique = true, nullable = false)
    private String cuit;

    //ENUM RUBRO
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String webSite;
    private String location;

    @OneToMany(mappedBy = "empresa")
    private List<OfferEntity> offers;

    @ManyToOne
    @JoinColumn(name = "experiencia_id")
    private LaboralExperienceEntity laboralExperiences;   // dudas al respecto.

    @OneToMany(mappedBy = "empresa")
    private List<PostsEntity> publications;

    @OneToMany(mappedBy = "empresa")
    private List<FollowingsEntity> follow_ups;
}