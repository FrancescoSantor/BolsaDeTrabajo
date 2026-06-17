package com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto;

import lombok.*;

import java.sql.Timestamp;
import java.util.UUID;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostLikesResponseDTO {
    private boolean liked;   //True si le dio me gusta, False si le quito el me gusta
    private int totalLikes;  //Devolvemos la cantidad actualziada de likes para que el front lo muestre directo
    private UUID externalId;
    private String userName;      // viene de user.name y es par ver quien interactuo
    private String postTitle;     // viene de post.title y es para saber sobre que post
    private Timestamp createdAt;  //Cuando/hace cuanto ocurrio
}