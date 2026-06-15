package com.Grupo15.BolsaDeTrabajo.Features.Message;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

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

        private boolean isRead; //leido


        private Timestamp createdAt;

        @PrePersist
        public void CreatedAt (){
                this.createdAt = Timestamp.from(Instant.now());
        }

}
