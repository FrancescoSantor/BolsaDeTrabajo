package com.Grupo15.BolsaDeTrabajo.Features.Users;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Message.MessageEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Notification.NotificationEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.PostLikesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Following.FollowingsEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import lombok.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter

@Inheritance(strategy = InheritanceType.JOINED)
public abstract class UsersEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column (nullable = false)
    private boolean active;

    private Timestamp createdAt;

    @OneToMany(mappedBy = "user")
    private List<NotificationEntity> notifications;

    @OneToMany(mappedBy = "issuer")
    private List<MessageEntity> issued_messages; //mensajes emitidos

    @OneToMany(mappedBy = "receptor")
    private List<MessageEntity> received_messages;

    @OneToMany(mappedBy = "user")
    private List<PostLikesEntity> likes;

    @OneToMany(mappedBy = "user")
    private List<CommentsEntity> comments;

    @OneToMany(mappedBy = "follower")
    private List<FollowingsEntity> followings;

    @PrePersist
    protected void create (){
        this.createdAt = Timestamp.from(Instant.now());
    }
}


