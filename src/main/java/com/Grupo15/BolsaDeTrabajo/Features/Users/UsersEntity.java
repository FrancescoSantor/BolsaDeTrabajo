package com.Grupo15.BolsaDeTrabajo.Features.Users;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.CandidatosEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class UsersEntity {

    @Entity
    @Table(name = "usuario")
    @Data
    @NoArgsConstructor
    public class Usuario {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String nombre;
        private String apellido;

        @Column(unique = true)
        private String email;

        private String passwordHash;
        private boolean activo;

        private Timestamp createdAt;

        @OneToOne(mappedBy = "usuario")
        private CandidatosEntity perfilCandidato;

        @OneToOne(mappedBy = "usuario")
        private EmpresasEntity perfilEmpresa;
    }

}
