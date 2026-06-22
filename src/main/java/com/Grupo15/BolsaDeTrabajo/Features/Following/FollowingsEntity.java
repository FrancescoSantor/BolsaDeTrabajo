package com.Grupo15.BolsaDeTrabajo.Features.Following;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;

import lombok.*;


@Entity
@Table(name = "seguimientos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FollowingsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    FollowState state;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsersEntity follower;

    @ManyToOne
    @JoinColumn(name = "usuario_seguido_id")
    private UsersEntity followed;

    private LocalDateTime createdAt;
}