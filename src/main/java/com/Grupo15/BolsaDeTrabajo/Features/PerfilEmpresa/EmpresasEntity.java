package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

import com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.ExperienciaLaboralEntity;
import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulacionEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Seguimientos.SeguimientosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.util.List;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "perfil_empresa")
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
    private String rubro;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    private String sitioWeb;
    private String ubicacion;

    @OneToMany(mappedBy = "empresa")
    private List<OfertaEntity> ofertas;

    @ManyToOne
    @JoinColumn(name = "experiencia_id")
    private ExperienciaLaboralEntity experienciaLaboral;

    @OneToMany(mappedBy = "empresa")
    private List<PostulacionEntity> publicaciones;

    @OneToMany(mappedBy = "empresa")
    private List<SeguimientosEntity> seguimientos;
}