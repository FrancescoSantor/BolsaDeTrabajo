package com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(target = "offers", ignore = true)
    @Mapping(target = "publications", ignore = true)
    CompaniesEntity toEntity (CompanyNewDTO newDTO);

    CompanyResponseDTO toDTO (CompaniesEntity entity);


}