package com.Grupo15.BolsaDeTrabajo.Features.Projects;

import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(target ="candidate",ignore = true)
    @Mapping(target ="id",ignore = true)
    public ProjectEntity toEntity(ProjectRequestDTO requestDTO);

    @Mapping(target = "candidateId", source = "candidate.name")
    public ProjectResponseDTO toDto(ProjectEntity project);
}
