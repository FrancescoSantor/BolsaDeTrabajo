package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Company.CompaniesEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.UUID;

import lombok.*;


@Entity
@Table(name = "experiencia_laboral")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaboralExperienceEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private UUID externalId;

    //CONEXION CON EMPRESA??
    @Column(nullable = false, length = 100)
    private String company;

    @Column(nullable = false, length = 100)
    private String position; //cargo

    @Column(name = "initial_date", nullable = false)
    private LocalDate initialDate;

    @Column(name = "end_date")
    private LocalDate endDate;


    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private CandidatesEntity candidate;   // dudas


    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private CompaniesEntity companyReferences;  // dudas
}