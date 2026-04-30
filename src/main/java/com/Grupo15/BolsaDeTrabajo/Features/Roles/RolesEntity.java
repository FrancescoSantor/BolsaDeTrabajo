package com.Grupo15.BolsaDeTrabajo.Features.Roles;

import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class RolesEntity {

    @Entity
    @Table(name = "roles")
    @Data
    @NoArgsConstructor
    public class Rol {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String rol;

        @ManyToOne
        @JoinColumn(name = "id_usuario")
        private UsersEntity usuario;
    }

}
