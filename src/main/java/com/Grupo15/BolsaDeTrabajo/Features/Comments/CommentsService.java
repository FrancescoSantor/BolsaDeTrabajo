package com.Grupo15.BolsaDeTrabajo.Features.Comments;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Post.PostRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Post.PostsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UserRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommentsService {

    private final CommentsRepository commentsRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentsResponseDTO createComment(CommentsNewDTO newDTO){

        PostsEntity post = postRepository.findByExternalId(newDTO.post_externalId())
                .orElseThrow(/*Tiras exception de post not found*/);

        UsersEntity user = userRepository.findByExternalId(newDTO.user_externalId())
                .orElseThrow(/*Usuario not found*/);


         if(!post.isActive()){
             /*TIRAS EXCEPTION DE NO SE PUEDE COMENTAR A ESTA PUBLICACION*/
         }

         if(!user.isActive()){
             //TIRAS USUARIO INACTIVO EXCEPTION
         }

         CommentsEntity comment = new CommentsEntity();

         comment.setPost(post);
         comment.setUser(user);
         comment.setContent(newDTO.content());
         comment.setActive(true);

         commentsRepository.save(comment);

         return new CommentsResponseDTO(comment.getUser().getName(), comment.getContent(), comment.getCreatedAt());


    }

    @Transactional
    public CommentsResponseDTO updateComment (UUID comment_externalId, String content){

        CommentsEntity comment = commentsRepository.findByExternalId(comment_externalId)
                .orElseThrow(/*tras not found exception*/);

        if (!comment.isActive()){
            //tiras coment not active exception
        }

        comment.setContent(content);

        commentsRepository.save(comment);

        return new CommentsResponseDTO(comment.getUser().getName(),
                comment.getContent(),
                comment.getCreatedAt());
    }

    @Transactional
    public void DeleteComent (UUID comment_externalId){

        CommentsEntity comment = commentsRepository.findByExternalId(comment_externalId)
                .orElseThrow(/*NOT FOUND EXCEPTION*/);

        if (!comment.isActive()){
            //tiras exception de que el comentario ya esta dado de baja
        }
        comment.setActive(false);
        commentsRepository.save(comment);


    }

    public List<CommentsResponseDTO> ListCommentsByPost (UUID PostExternalId){
        return commentsRepository.findByPostExternalId(PostExternalId)
                .stream()
                .map(comment -> new CommentsResponseDTO(
                        comment.getUser().getName(),
                        comment.getContent(),
                        comment.getCreatedAt()
                ))
                .toList();
    }


}
