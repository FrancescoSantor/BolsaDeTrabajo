package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.BussinesRulesException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.InvalidPasswordException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ResourceAlreadyExistsException;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferStatus;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Mapper.CompanyMapper;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompaniesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.Role;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.RoleEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsRepository;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServices {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepositorySecurity;


    @Transactional
    public CompanyResponseDTO create_Company (CompanyNewDTO newDTO){

        if(companyRepository.existsByCuit(newDTO.cuit())){
            throw new ResourceAlreadyExistsException("the cuit that you want to register already exists");
        }
        if (companyRepository.existsByEmail(newDTO.email())){
            throw new ResourceAlreadyExistsException("The email that you want to register already exists");
        }

        if (credentialsRepository.existsByUsername(
                newDTO.username())) {

            throw new RuntimeException(
                    "El username ya existe");
        }

        if (newDTO.password() == null ||
                newDTO.password().length() < 8) {

            throw new InvalidPasswordException(
                    "The password must be at least 8 characters long.");
        }

        CompaniesEntity companies = companyMapper.toEntity(newDTO);
        companies.setActive(true);
        companies.setName(newDTO.name());
        companies.setEmail(newDTO.email());

        companyRepository.save(companies);

        RoleEntity securityRole = roleRepositorySecurity
                .findByRole(Role.ROLE_COMPANY)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        CredentialsEntity credentials =
                CredentialsEntity.builder()
                        .username(newDTO.username())
                        .password(
                                passwordEncoder.encode(
                                        newDTO.password()
                                )
                        )
                        .enabled(true)
                        .usuario(companies)
                        .roles(Set.of(securityRole))
                        .build();

        credentialsRepository.save(credentials);

        return companyMapper.toDTO(companies);

    }


    @Transactional(readOnly = true)
    public Page<CompanyResponseDTO> ListCompanies (String name, String email, String location, Category category, Pageable pageable){

        Page<CompaniesEntity> companyPage = companyRepository.findByFilters(name,email,category,location,pageable);

        return companyPage.map(companyMapper::toDTO);

    }


    @Transactional
    public CompanyResponseDTO DeleteCompany(UUID externalId){

        CompaniesEntity Company = companyRepository.findByExternalId(externalId)
                .orElseThrow(() -> new ElementNotFoundException("The company that you want to delete does not exists"));

        if(Company
                .getOffers()
                .stream()
                .anyMatch(offerEntity -> offerEntity.getOfferStatus() == OfferStatus.OPEN)){

            throw new BussinesRulesException("you cant delete a company whit offers in open state");

        }

        Company.setActive(false);

        //se dan de baja los post de la empresa

        Company.getPublications().forEach(Post -> Post.setActive(false));

        companyRepository.save(Company);

        return companyMapper.toDTO(Company);

    }


    @Transactional
    public CompanyResponseDTO UpdateCompany (CompaniesRequestDTO atUpdate){

        CompaniesEntity company = companyRepository.findByCuit(atUpdate.cuit())
                .orElseThrow(() -> new ElementNotFoundException("the company that you want to update does not exists"));

        if (atUpdate.name() != null){
            company.setName(atUpdate.name());
        }
        if (atUpdate.email() != null){
            company.setEmail(atUpdate.email());
        }
        if (atUpdate.category() != null){
            company.setCategory(atUpdate.category());
        }
        if (atUpdate.description() != null){
            company.setDescription(atUpdate.description());
        }
        if (atUpdate.webSite() != null){
            company.setWebSite(atUpdate.webSite());
        }
        if (atUpdate.location() != null){
            company.setLocation(atUpdate.location());
        }

        companyRepository.save(company);

        return companyMapper.toDTO(company);
    }

    @Transactional(readOnly = true)
    public CompanyResponseDTO getById(UUID externalId){

        return companyRepository.findByExternalId(externalId)
                .map(companyMapper::toDTO)
                .orElseThrow(() -> new ElementNotFoundException("this company does not exists"));

    }








}
