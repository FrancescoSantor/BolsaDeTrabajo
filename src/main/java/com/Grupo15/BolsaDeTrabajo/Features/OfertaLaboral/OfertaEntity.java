package com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral;

import com.Grupo15.BolsaDeTrabajo.Features.Guardados.GuardadosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaHabilidad.OfertaHabilidadEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulacionEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.PublicacionesEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.annotation.Id;

@Entity
@Table(name = "oferta_laboral")
public class OfertaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresasEntity empresa;

    //ENUM DE TIPO DE TITULO
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String ubicacion;

    //ENUM DE MODALIDAD
    private String modalidad;

    //???
    private String tipoContrato;

    @Column(nullable = false)
    private Double salarioMin;
    @Column(nullable = false)
    private Double salarioMax;

    //ENUM DE ESTADO DE LA OFERTA
    @Column(nullable = false)
    private String estado;

    private Timestamp fechaPublicacion;
    private Timestamp fechaCierre;

    @OneToMany(mappedBy = "oferta")
    private List<PostulacionEntity> postulaciones;

    @OneToMany(mappedBy = "oferta")
    private List<OfertaHabilidadEntity> habilidades;

    @OneToMany(mappedBy = "oferta")
    private List<GuardadosEntity> guardados;

    @OneToMany(mappedBy = "oferta")
    private List<PublicacionesEntity> publicaciones;
}


