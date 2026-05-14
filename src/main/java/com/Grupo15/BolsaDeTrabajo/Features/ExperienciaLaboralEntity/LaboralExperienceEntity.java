package com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import jakarta.persistence.*;

import java.sql.Date;

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

    private Date initialDate;
    private Date endDate;

    private boolean currentWork; //trabajo actual

    @Column(columnDefinition = "TEXT")
    private String description;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private CompaniesEntity companyReferences;  // dudas
}