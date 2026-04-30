package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

import com.Grupo15.BolsaDeTrabajo.Features.OfertaLaboral.OfertaLaboralEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class EmpresasEntity {

    @Entity
    @Table(name = "perfil_empresa")
    @Data
    @NoArgsConstructor
    public class PerfilEmpresa {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @OneToOne
        @JoinColumn(name = "usuario_id")
        private UsersEntity usuario;

        private String razonSocial;

        @Column(unique = true)
        private String cuit;

        private String rubro;

        @Column(columnDefinition = "TEXT")
        private String descripcion;

        private String logoUrl;
        private String sitioWeb;
        private String ubicacion;

        @OneToMany(mappedBy = "empresa")
        private List<OfertaLaboralEntity> ofertas;
    }

}
