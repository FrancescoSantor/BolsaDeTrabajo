package com.Grupo15.BolsaDeTrabajo.Features.Post;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.BaseEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Company.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.PostLikesEntity;
import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.List;

import lombok.*;

@Entity
@Table(name = "publicaciones")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostsEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private CompaniesEntity company;

    @ManyToOne
    @JoinColumn(name = "oferta_id")
    private OfferEntity offer;


    private String title;

    @Column(columnDefinition = "TEXT")
    private String content;

    private String urlImage;

    private int totalLikes;
    private int totalComments;

    private boolean active;

    private Timestamp createdAt;
    private Timestamp updatedAt;

    @OneToMany(mappedBy = "post")
    private List<PostLikesEntity> likes;

    @OneToMany(mappedBy = "post")
    private List<CommentsEntity> comments;
}


