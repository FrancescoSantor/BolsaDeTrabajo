package com.Grupo15.BolsaDeTrabajo.Features.Comments;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.BussinesRulesException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
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
                .orElseThrow(() -> new ElementNotFoundException("The post to comment does not exists"));

        UsersEntity user = userRepository.findByExternalId(newDTO.user_externalId())
                .orElseThrow(() -> new ElementNotFoundException("this user does not exists"));


         if(!post.isActive()){
             throw new BussinesRulesException("you cant comment a post that is inactive");
         }

         if(!user.isActive()){
             throw new BussinesRulesException("an user that is inactive cant comment");
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
                .orElseThrow(() -> new ElementNotFoundException("the comment to update not exists"));

        if (!comment.isActive()){
            throw new BussinesRulesException("the coment is not active");
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
                .orElseThrow(() -> new ElementNotFoundException("the comment to delete does not exists"));

        if (!comment.isActive()){
            throw new BussinesRulesException("the comment to delete is already exists");
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
