package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {


    @Mapping(target = "name", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "active", ignore = true)
    @Mapping(target = "rol", ignore = true)
    @Mapping(target = "offers", ignore = true)
    @Mapping(target = "publications", ignore = true)
    @Mapping(target = "followUps", ignore = true)
    @Mapping(target = "notifications", ignore = true)
    @Mapping(target = "issued_messages", ignore = true)
    @Mapping(target = "likes", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "followings", ignore = true)
    CompaniesEntity toEntity (CompanyNewDTO newDTO);

    CompanyResponseDTO toDTO (CompaniesEntity entity);



}
