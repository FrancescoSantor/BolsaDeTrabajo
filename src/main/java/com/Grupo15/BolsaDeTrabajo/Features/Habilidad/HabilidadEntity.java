package com.Grupo15.BolsaDeTrabajo.Features.Habilidad;

import jakarta.persistence.*;
import java.sql.Timestamp;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


public class HabilidadEntity {

    @Entity
    @Table(name = "habilidad")
    @Data
    @NoArgsConstructor
    public class Habilidad {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(unique = true)
        private String nombre;

        private String categoria;
    }

}
