package com.Grupo15.BolsaDeTrabajo.Features.Roles;

import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "roles")
public class RolesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ENUM DE ROL (EMPLEADO/EMPRESA)
    private String rol;

    //SOLUCION DE RELACION ROL/USUARIO
    @OneToMany(mappedBy = "rol")
    private List<UsersEntity> usuario;
}