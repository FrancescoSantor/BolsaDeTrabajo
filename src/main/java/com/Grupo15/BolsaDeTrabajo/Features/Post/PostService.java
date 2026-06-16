package com.Grupo15.BolsaDeTrabajo.Features.Post;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferStatus;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompanyRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostsRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PostLikes.PostLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    private final CompanyRepository companyRepository;
    private final OfferRepository offerRepository;

    public PostResponseDTO create(PostsRequestDTO requestDTO) {
        CompaniesEntity company = companyRepository.findById(requestDTO.companyId())
                .orElseThrow(() -> new RuntimeException("The Company has not been found.")); //ResourceNotFoundException

        if(!company.isActive()) {
            throw new RuntimeException("The Company is not active, so the post cannot be created."); //BusinessRuleException
        }

        OfferEntity offer = offerRepository.findById(requestDTO.offerId())
                .orElseThrow(() -> new RuntimeException("The Offer has not been found.")); //ResourceNotFoundException

        if(offer.getStatus().equals(OfferStatus.CLOSE)) {
            throw new RuntimeException("The Offer is already ended, so the post cannot be created."); //BusinessRuleException
        }

        if(postRepository.existsByCommpanyIdAndOfferId(requestDTO.companyId(), requestDTO.offerId())) {
            throw new RuntimeException("The post has already been created."); //ResourceExistsException
        }
        if(requestDTO.title() == null || requestDTO.title().isBlank()) {
            throw new RuntimeException("The post has no title."); //BusinessRuleException
        }
        if(requestDTO.content() == null || requestDTO.content().isBlank()) {
            throw new RuntimeException("The post has no content."); //BusinessRuleException
        }
        if(requestDTO.urlImage() == null || requestDTO.urlImage().isBlank()) {
            throw new RuntimeException("The post has no Url image."); //BusinessRuleException
        }

        PostsEntity post = postMapper.toEntity(requestDTO);
        post.setCompany(company);
        post.setOffer(offer);
        post.setTitle(requestDTO.title());
        post.setContent(requestDTO.content());
        post.setTotalLikes(0);
        post.setTotalComments(0);
        post.setActive(true);
        post.setUrlImage(requestDTO.urlImage());
        post.setCreatedAt(Timestamp.from(Instant.now()));

        return postMapper.toDto(postRepository.save(post));
    }

    public PostResponseDTO updatePost (UUID externalId, String title, String content, String urlImage) {
        PostsEntity post = postRepository.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("The post has not been found.")); //ResourceNotFoundException

        if(!post.getCompany().isActive()) {
            throw new RuntimeException("The Company is not active, so the post cannot be update."); //BusinessRuleException
        }

        if(post.getOffer().getStatus().equals(OfferStatus.CLOSE)) {
            throw new RuntimeException("The Offer is already ended, so the post cannot be update."); //BusinessRuleException
        }

        if(!title.isBlank()) {
            post.setTitle(title);
        }
        if(!content.isBlank()) {
            post.setContent(content);
        }
        if(!urlImage.isBlank()) {
            post.setUrlImage(urlImage);
        }
        post.setUpdatedAt(Timestamp.from(Instant.now()));

        return postMapper.toDto(postRepository.save(post));
    }

    public PostResponseDTO deletePost (UUID externalId) {
        PostsEntity post = postRepository.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("The post has not been found.")); //ResourceNotFoundException

       /*if(!post.getCompany().isActive()) {
            throw new RuntimeException("The Company is not active, so the post cannot be created.");
        }
        if(!post.isActive()) {
            throw new RuntimeException("The post has already been deactivated.");
        }*/

        post.setUpdatedAt(Timestamp.from(Instant.now()));
        post.setActive(false);

        return postMapper.toDto(postRepository.save(post));
    }

    public List<CommentsEntity> viewComentsPost (UUID externalId) {
        PostsEntity post = postRepository.findByExternalId(externalId)
                .orElseThrow(() -> new RuntimeException("The post has not been found.")); //ResourceNotFoundException

        return post.getComments().stream()
                //.map(a -> a.getContent())
                .toList();
    }
}
