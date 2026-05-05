package com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral;

import com.Grupo15.BolsaDeTrabajo.Features.Guardados.GuardadosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaHabilidad.OfertaHabilidadEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.Title;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulacionEntity;
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
public class OfertaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresasEntity empresa;

    //ENUM DE TIPO DE TITULO
    @Enumerated(EnumType.STRING)
    private Title titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String ubicacion;

    //ENUM DE MODALIDAD
    @Enumerated(EnumType.STRING)
    private OfferType modalidad;

    //???
    private String tipoContrato;

    @Column(nullable = false)
    private Double salarioMin;
    @Column(nullable = false)
    private Double salarioMax;

    //ENUM DE ESTADO DE LA OFERTA
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OfferStatus estado;

    private Timestamp fechaPublicacion;
    private Timestamp fechaCierre;

    @OneToMany(mappedBy = "oferta")
    private List<PostulacionEntity> postulaciones;

    @OneToMany(mappedBy = "oferta")
    private List<OfertaHabilidadEntity> habilidades;

    @OneToMany(mappedBy = "oferta")
    private List<GuardadosEntity> guardados;

    // habia un onetomany a publicaciones que decidimos sacarlo.
}


