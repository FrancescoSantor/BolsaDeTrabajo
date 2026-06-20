package com.Grupo15.BolsaDeTrabajo.Features.PostLikes;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.Post.PostRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Post.PostsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.Mapper.PostLikeMapper;
import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto.PostLikesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.dto.PostLikesResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UserRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostLikeServiceImpl implements PostLikeService{

    private final PostLikeRepository postLikeRepository;
    private final PostRepository postRepository;
    private final UserRepository usersRepository;
    private final PostLikeMapper postLikeMapper;


    @Override
    @Transactional
    public PostLikesResponseDTO toggleLike(PostLikesRequestDTO requestDto) {

        // Buscamos el Post por su UUID seguro provisto por el frontend
        PostsEntity post = postRepository.findByExternalId(requestDto.postExternalId())
                                                                //El post no existe.
                .orElseThrow(() -> new ElementNotFoundException("El post no existe."));

        // Buscamos al usuario que hace la acción
        UsersEntity user = usersRepository.findById(requestDto.userId())
                                                                //El usuario no existe.
                .orElseThrow(() -> new ElementNotFoundException("El usuario no existe."));

        // Verificamos si este usuario ya le había dado like a este post anteriormente
        Optional<PostLikesEntity> existingLike = postLikeRepository.findByUserIdAndPostId(user.getId(), post.getId());

        //Logica
        if (existingLike.isPresent()) {

            // Ya tenía Like -> Se lo removemos (Dislike)
            // Borramos de la tabla intermedia
            postLikeRepository.delete(existingLike.get());

            // Restamos 1 al contador del post (asegurando que no baje de 0)
            int nuevosLikes = Math.max(0, post.getTotalLikes() - 1);
            post.setTotalLikes(nuevosLikes);
            // Sincronizamos la tabla de publicaciones
            postRepository.save(post);

            // Retornamos la respuesta limpia e informativa de desvinculación
            return PostLikesResponseDTO.builder()
                    .liked(false)
                    .totalLikes(nuevosLikes)
                    .externalId(null) // Ponemos null porque el registro ya no existe en la BD
                    .userName(user.getName())
                    .postTitle(post.getTitle())
                    .createdAt(Timestamp.valueOf(LocalDateTime.now())) // Fecha del momento del dislike
                    .build();

        } else {
            // No tenía Like -> Se lo agregamos
            PostLikesEntity nuevoLike = PostLikesEntity.builder()
                    .user(user)
                    .post(post)
                    .createdAt(Timestamp.valueOf(LocalDateTime.now())) // Fecha manual para no chocar con BaseEntity
                    .build();

            // Guardamos el registro
            PostLikesEntity savedLike = postLikeRepository.save(nuevoLike);

            // Sumamos 1 al contador del post
            int nuevosLikes = post.getTotalLikes() + 1;
            post.setTotalLikes(nuevosLikes);
            postRepository.save(post); // Sincronizamos la tabla de publicaciones

            PostLikesResponseDTO response = postLikeMapper.toDto(savedLike);

            // Metemos/inyectamos manualmente los dos estados
            response.setLiked(true);
            response.setTotalLikes(nuevosLikes);

            return response;
        }
    }



}
