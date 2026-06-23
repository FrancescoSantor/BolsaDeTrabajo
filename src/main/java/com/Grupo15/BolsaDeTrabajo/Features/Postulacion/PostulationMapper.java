package com.Grupo15.BolsaDeTrabajo.Features.Postulacion;

import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.DTO.PostulationResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface PostulationMapper {
    @Mapping(target = "coverLetter", source = "postulation.coverLetter")
    PostulationResponseDTO toDto (PostulationsEntity postulation);
    /// CONSIDERO EL MAPPER INUTIL YA QUE SOLO LLEVA EL COVERLETTER



}
