package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Mapper.CompanyMapper;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RoleRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.Roles;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
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

        RolesEntity rol = roleRepository.findByRol(Roles.COMPANY)
                        .orElseThrow(() -> //exception)



        companyRepository.save(companies);

        return companyMapper.toDTO(companies);

    }



}
