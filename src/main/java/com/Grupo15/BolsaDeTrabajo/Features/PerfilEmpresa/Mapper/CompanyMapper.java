package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilCandidato.dto.CandidatesResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompaniesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompaniesResponseDTO;

public class CompanyMapper {

    public static CompaniesResponseDTO toDto(CompaniesEntity companiesEntity) {
        return CompaniesResponseDTO.builder()
                .externalId(companiesEntity.getExternalId())
                .name(companiesEntity.getName())
                .lastName(companiesEntity.getLastName())
                .email(companiesEntity.getEmail())
                .registeredName(companiesEntity.getRegisteredName())
                .cuit(companiesEntity.getCuit())
                .category(companiesEntity.getCategory())
                .description(companiesEntity.getDescription())
                .webSite(companiesEntity.getWebSite())
                .location(companiesEntity.getLocation())
                .build();
    }

    public static CompaniesEntity toEntity (CompaniesRequestDTO request){
        return CompaniesEntity.builder()
                .id(request.userId())
                .registeredName(request.registeredName())
                .cuit(request.cuit())
                .category(request.category())
                .description(request.description())
                .webSite(request.webSite())
                .location(request.location())
                .build();
    }
}
