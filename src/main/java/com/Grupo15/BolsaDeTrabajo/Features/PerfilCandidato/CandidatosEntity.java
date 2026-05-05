package com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato;

import com.Grupo15.BolsaDeTrabajo.Features.CandidatoHabilidad.CandidatoHabilidadEntity;
import com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.ExperienciaLaboralEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Guardados.GuardadosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulacionEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.*;

@Entity
@Table(name = "perfil_candidato")@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CandidatosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity usuario;

    //ENUM DE TITULO(EJ INGENIERO TECNICO LICENCIADO)
    @Enumerated(EnumType.STRING)
    private Title tituloProfesional;

    @Column(columnDefinition = "TEXT")
    private String resumen;

    private String cvUrl;
    private String linkedinUrl;
    private String fotoUrl;

    private Timestamp updatedAt;

    @OneToMany(mappedBy = "candidato")
    private List<PostulacionEntity> postulaciones;

    //relacion de candidatoHabilidad
    @OneToMany(mappedBy = "candidato")
    private List<CandidatoHabilidadEntity> candidatoHabilidad;

    @OneToMany(mappedBy = "candidato")
    private List<ExperienciaLaboralEntity> experienciaLaboral;

    @OneToMany(mappedBy = "candidato")
    private List<GuardadosEntity> guardados;
}


