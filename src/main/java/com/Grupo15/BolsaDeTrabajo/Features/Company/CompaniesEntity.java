package com.Grupo15.BolsaDeTrabajo.Features.Company;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Post.PostsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.SavedEntity;
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
public class CompaniesEntity extends UsersEntity {

    /*
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity user;
     */

    @Column(unique = true, nullable = false)
    private String cuit;

    //ENUM RUBRO
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false,unique = true)
    private String webSite;

    @Column(nullable = false)
    private String location;

    @OneToMany(mappedBy = "company")
    private List<OfferEntity> offers;

    @OneToMany(mappedBy = "company")
    private List<SavedEntity> saveds;

//    @ManyToOne
//    @JoinColumn(name = "experiencia_id")
//    private LaboralExperienceEntity laboralExperiences;   // dudas al respecto.

    @OneToMany(mappedBy = "company")
    private List<PostsEntity> publications;

//    @OneToMany(mappedBy = "company")
//   private List<FollowingsEntity> follow_ups;
}