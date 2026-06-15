package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferStatus;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Mapper.CompanyMapper;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompaniesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.Roles;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsRepository;
import com.Grupo15.BolsaDeTrabajo.Features.auth.dto.NewAccountRequest;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.Role;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.RoleEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServices {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final RolesRepository rolesRepository;
    private final CredentialsRepository credentialsRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepositorySecurity;


    @Transactional
    public CompanyResponseDTO create_Company (CompanyNewDTO newDTO){

        if(companyRepository.existsByCuit(newDTO.cuit())){
            //rebolea excepcion de ya existente
        }
        if (companyRepository.existsByEmail(newDTO.email())){
            //reboleas excepcion de ya existente
        }

        if (credentialsRepository.existsByUsername(
                newDTO.username())) {

            throw new RuntimeException(
                    "El username ya existe");
        }


        CompaniesEntity companies = companyMapper.toEntity(newDTO);
        companies.setActive(true);
        companies.setName(newDTO.name());
        companies.setEmail(newDTO.email());
        //companies.setPassword(newDTO.password());

        RolesEntity rol = rolesRepository.findByRol(Roles.COMPANY)
                .orElseThrow(
                        //REBOLEAS NOT FOUND EXCEPTION
                );

        companies.setRol(rol);

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
                .orElseThrow(/*REBOLEAS EXCEPCION DE USUARIO NO EXISTENTE*/);

        if(Company
                .getOffers()
                .stream()
                .anyMatch(offerEntity -> offerEntity.getStatus() == OfferStatus.OPEN)){

            /*TIRAS EXCEPCION DE REGLA DE NEGOCIO NO SE PUEDEN ELIMINAR EMPRESA CON OFERTAS ABIERTAS*/

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
                .orElseThrow(/*REBOLEAS NOT FOUND EXCEPTION*/);

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
                .orElseThrow(/*ARROJAS EXCEPTION DE NOT FOUND*/);

    }








}
