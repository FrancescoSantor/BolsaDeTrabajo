package com.Grupo15.BolsaDeTrabajo.Features.Seguimientos;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.EmpresasEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;

import lombok.*;


@Entity
@Table(name = "seguimientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeguimientosEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity usuario;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private EmpresasEntity empresa;

    private Timestamp createdAt;
}