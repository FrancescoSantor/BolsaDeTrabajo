package com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral;

import com.Grupo15.BolsaDeTrabajo.Features.Commons.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Guardados.SavedEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaHabilidad.Ability_x_OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.Title;
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

    @OneToMany(mappedBy = "oferta")
    private List<PostulationsEntity> applications;

    @OneToMany(mappedBy = "oferta")
    private List<Ability_x_OfferEntity> abilities;

    @OneToMany(mappedBy = "oferta")
    private List<SavedEntity> saved;

    // habia un onetomany a publicaciones que decidimos sacarlo.
}


