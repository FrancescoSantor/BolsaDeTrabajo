package com.Grupo15.BolsaDeTrabajo.Features.Guardados;

import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

import lombok.*;

@Entity
@Table(name = "guardados")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuardadosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "candidato_id")
    private CandidatosEntity candidato;

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private OfertaEntity oferta;

    private Timestamp createdAt;
}