package com.Grupo15.BolsaDeTrabajo.Features.Mensajes;

import com.Grupo15.BolsaDeTrabajo.Features.Commons.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;

import lombok.*;

@Entity
@Table(name = "mensajes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessagesEntity extends BaseEntity{


        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @ManyToOne
        @JoinColumn(name = "emisor_id")
        private UsersEntity issuer; //emisor

        @ManyToOne
        @JoinColumn(name = "receptor_id")
        private UsersEntity receptor;

        @Column(columnDefinition = "TEXT")
        private String content;

        private boolean read; //leido

        private Timestamp createdAt;
    }
