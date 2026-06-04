package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private CandidatesEntity candidate;   // dudas

    //CONEXION CON EMPRESA??
    private String company;

    private String position; //cargo

    private LocalDate initialDate;
    private LocalDate endDate;


    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private CompaniesEntity companyReferences;  // dudas
}