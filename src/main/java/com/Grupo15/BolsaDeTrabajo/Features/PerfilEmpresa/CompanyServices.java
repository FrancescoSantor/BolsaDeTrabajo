package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.OfferStatus;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Mapper.CompanyMapper;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompaniesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RoleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServices {

    private final CompanyRepository companyRepository;
    private final CompanyMapper companyMapper;
    private final RoleRepository roleRepository;


    @Transactional
    public CompanyResponseDTO create_Company (CompanyNewDTO newDTO){

        if(companyRepository.existsByCuit(newDTO.cuit())){
            //rebolea excepcion de ya existente
        }
        if (companyRepository.existsByEmail(newDTO.email())){
            //reboleas excepcion de ya existente
        }

        CompaniesEntity companies = companyMapper.toEntity(newDTO);
        companies.setActive(true);
        companies.setName(newDTO.name());
        companies.setEmail(newDTO.email());
        companies.setPassword(newDTO.password());

        /*RolesEntity rol = roleRepository.findByRol(Roles.COMPANY)
                        .orElseThrow(() -> /*exception);
        */
        companyRepository.save(companies);

        return companyMapper.toDTO(companies);

    }


    public Page<CompanyResponseDTO> ListCompanies (String name, String email, String location, Category category, Pageable pageable){

        Page<CompaniesEntity> companyPage = companyRepository.findByFilters(name,email,category,location,pageable);

        return companyPage.map(companyMapper::toDTO);

    }


    public CompanyResponseDTO DeleteCompany(CompaniesRequestDTO requestDTO){

        CompaniesEntity Company = companyRepository.findByCuit(requestDTO.cuit())
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








}
