package com.Grupo15.BolsaDeTrabajo.Features.Roles;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name = "rol")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RolesEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //ENUM DE ROL (EMPLEADO/EMPRESA)
    @Enumerated(EnumType.STRING)
    private Roles rol;

    /*SOLUCION DE RELACION ROL/USUARIO
    @OneToMany(mappedBy = "rol")
    private List<UsersEntity> usuario;
    */
}