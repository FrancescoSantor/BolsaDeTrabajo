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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private CompaniesEntity company;

    //ENUM DE TIPO DE TITULO
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Title title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @Column(nullable = false)
    private String location;

    //ENUM DE MODALIDAD
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferType modality; //or mode

    @Column(name = "contract_type", nullable = false)
    private String contractType;

    @Column(nullable = false)
    private Double minSalary;
    @Column(nullable = false)
    private Double maxSalary;

    //ENUM DE ESTADO DE LA OFERTA
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferStatus offerStatus;

    @Column(nullable = false)
    private Timestamp publicationDate;

    @Column(nullable = false)
    private Timestamp publicationClosing;

    @OneToMany(mappedBy = "offer")
    private List<PostulationsEntity> applications;

    @OneToMany(mappedBy = "offer")
    private List<Ability_x_OfferEntity> abilities;

    @OneToMany(mappedBy = "offer")
    private List<SavedEntity> saved;

    @PrePersist
    protected void anCreate() {
        this.offerStatus = OfferStatus.OPEN;
    }
    // habia un onetomany a publicaciones que decidimos sacarlo.
}


