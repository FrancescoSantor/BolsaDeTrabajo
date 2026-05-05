package com.Grupo15.BolsaDeTrabajo.Features.Habilidad;

import com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad.CandidatoHabilidadEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaHabilidad.OfertaHabilidadEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.*;

@Entity
@Table(name = "habilidad")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HabilidadEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nombre;

    private String categoria;

    @OneToMany(mappedBy = "habilidades")
    private List<OfertaHabilidadEntity> ofertaHabilidadEntities; // tiene sentido que desde aca se pueda llamar ??

    @OneToMany(mappedBy = "habilidad")
    private List<CandidatoHabilidadEntity> candidatoHabilidad;   //   tiene sentido que desde aca se pueda llamar ??


    }


