package com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato;

import com.Grupo15.BolsaDeTrabajo.Features.ExperienciaLaboralEntity.ExperienciaLaboralEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class CandidatosEntity {

    @Entity
    @Table(name = "perfil_candidato")
    @Data
    @NoArgsConstructor
    public class PerfilCandidato {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn(name = "usuario_id")
        private UsersEntity usuario;

        private String tituloProfesional;

        @Column(columnDefinition = "TEXT")
        private String resumen;

        private String cvUrl;
        private String linkedinUrl;
        private String fotoUrl;

        private Timestamp updatedAt;

        @OneToMany(mappedBy = "candidato")
        private List<ExperienciaLaboralEntity> experiencias;
    }

}
