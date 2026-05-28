package com.Grupo15.BolsaDeTrabajo.Features.Offer;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Saved.SavedEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Ability_x_Offer.Ability_x_OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Title;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationsEntity;
import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "oferta_laboral")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OfferEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private CompaniesEntity company;

    //ENUM DE TIPO DE TITULO
    @Enumerated(EnumType.STRING)
    private Title title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String location;

    //ENUM DE MODALIDAD
    @Enumerated(EnumType.STRING)
    private OfferType modality; //or mode

    //???
    private String contractType;

    @Column(nullable = false)
    private Double minSalary;
    @Column(nullable = false)
    private Double maxSalary;

    //ENUM DE ESTADO DE LA OFERTA
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferStatus status;

    private Timestamp publicationDate;
    private Timestamp publicationClosing;

    @OneToMany(mappedBy = "offer")
    private List<PostulationsEntity> applications;

    @OneToMany(mappedBy = "offer")
    private List<Ability_x_OfferEntity> abilities;

    @OneToMany(mappedBy = "offer")
    private List<SavedEntity> saved;

    // habia un onetomany a publicaciones que decidimos sacarlo.
}


