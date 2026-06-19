package com.Grupo15.BolsaDeTrabajo.Features.Post;

import com.Grupo15.BolsaDeTrabajo.Features.Comments.mapper.CommentsMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.CommentsRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.dto.CommentsResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Comments.mapper.CommentsMapper;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferStatus;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompanyRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Post.Mappers.PostMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Post.dto.PostsRequestDTO;
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
    private final CommentsRepository commentsRepository;
    private final CommentsMapper commentsMapper;

    public PostResponseDTO create(PostsRequestDTO requestDTO) {
        CompaniesEntity company = companyRepository.findByExternalId(requestDTO.companyId())
                .orElseThrow(() -> new ElementNotFoundException("The Company has not been found."));

        if(!company.isActive()) {
            throw new RuntimeException("The Company is not active, so the post cannot be created."); //BusinessRuleException
        }

        OfferEntity offer = offerRepository.findByExternalId(requestDTO.offerId())
                .orElseThrow(() -> new ElementNotFoundException("The Offer has not been found."));

        if(offer.getOfferStatus().equals(OfferStatus.CLOSE)) {
            throw new RuntimeException("The Offer is already ended, so the post cannot be created."); //BusinessRuleException
        }

        if(postRepository.existsByCompanyExternalIdAndOfferExternalId(requestDTO.companyId(), requestDTO.offerId())) {
            throw new RuntimeException("The post has already been created."); //ResourceExistsException
        }
        if(requestDTO.title() == null || requestDTO.title().isBlank()) {
            throw new NullPointerException("The post has no title.");
        }
        if(requestDTO.content() == null || requestDTO.content().isBlank()) {
            throw new NullPointerException("The post has no content.");
        }
        if(requestDTO.urlImage() == null || requestDTO.urlImage().isBlank()) {
            throw new NullPointerException("The post has no Url image.");
        }

        PostsEntity post = postMapper.toEntity(requestDTO);
        post.setCompany(company);
        post.setOffer(offer);
        post.setTitle(requestDTO.title());
        post.setContent(requestDTO.content());
        post.setUrlImage(requestDTO.urlImage());
        post.setTotalLikes(0);
        post.setTotalComments(0);
        post.setActive(true);
        post.setCreatedAt(Timestamp.from(Instant.now()));

        return postMapper.toDto(postRepository.save(post));
    }

    public PostResponseDTO updatePost (UUID postId, String title, String content, String urlImage) {
        PostsEntity post = postRepository.findByExternalId(postId)
                .orElseThrow(() -> new ElementNotFoundException("The post has not been found."));

        if(!post.getCompany().isActive()) {
            throw new RuntimeException("The Company is not active, so the post cannot be update."); //BusinessRuleException
        }

        if(post.getOffer().getOfferStatus().equals(OfferStatus.CLOSE)) {
            throw new RuntimeException("The Offer is already ended, so the post cannot be update."); //BusinessRuleException
        }

        if(title != null && !title.isBlank()) {
            post.setTitle(title);
        }
        if(content != null && !content.isBlank()) {
            post.setContent(content);
        }
        if(urlImage != null && !urlImage.isBlank()) {
            post.setUrlImage(urlImage);
        }
        post.setUpdatedAt(Timestamp.from(Instant.now()));

        return postMapper.toDto(postRepository.save(post));
    }

    public PostResponseDTO deletePost (UUID postId) {
        PostsEntity post = postRepository.findByExternalId(postId)
                .orElseThrow(() -> new ElementNotFoundException("The Post has not been found."));

       if(!post.getCompany().isActive()) {
            throw new RuntimeException("The Company is not active, so the post cannot be deleted.");
        }

        if(!post.isActive()) {
            throw new RuntimeException("The post has already been deleted.");
        }

        post.setUpdatedAt(Timestamp.from(Instant.now()));
        post.setActive(false);

        return postMapper.toDto(postRepository.save(post));
    }

    public PostResponseDTO getPost (UUID postId) {
        PostsEntity post = postRepository.findByExternalId(postId)
                .orElseThrow(() -> new ElementNotFoundException("The Post has not been found."));

        return postMapper.toDto(post);
    }

    public List<PostResponseDTO> getAllPostByCompany (UUID companyId) {
        CompaniesEntity company = companyRepository.findByExternalId(companyId) // aqui podria ir un if(!companyRepository.existsByExternalId(companyId){}
                .orElseThrow(() -> new ElementNotFoundException("The Company has not been found."));

        return postRepository.findAllByCompanyExternalId(companyId)
                .stream()
                .map(postMapper::toDto)
                .toList();
    }

    public List<CommentsResponseDTO> getCommentsByPost (UUID postId) {
        if(!postRepository.existsByExternalId(postId)) {
            throw new ElementNotFoundException("The Post has not been found.");
        }

        return commentsRepository.findByPostExternalId(postId)
                .stream()
                .map(commentsMapper::toDTO)
                .toList();
    }
}
