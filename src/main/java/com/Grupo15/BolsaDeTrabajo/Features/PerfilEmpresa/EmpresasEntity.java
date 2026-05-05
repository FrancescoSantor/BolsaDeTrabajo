package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

import com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.ExperienciaLaboralEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Publicaciones.PublicacionesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Seguimientos.SeguimientosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "perfil_empresa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmpresasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity usuario;

    //??
    private String razonSocial;

    @Column(unique = true, nullable = false)
    private String cuit;

    //ENUM RUBRO
    @Enumerated(EnumType.STRING)
    private Category rubro;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String sitioWeb;
    private String ubicacion;

    @OneToMany(mappedBy = "empresa")
    private List<OfertaEntity> ofertas;

    @ManyToOne
    @JoinColumn(name = "experiencia_id")
    private ExperienciaLaboralEntity experienciaLaboral;   // dudas al respecto.

    @OneToMany(mappedBy = "empresa")
    private List<PublicacionesEntity> publicaciones;

    @OneToMany(mappedBy = "empresa")
    private List<SeguimientosEntity> seguimientos;
}