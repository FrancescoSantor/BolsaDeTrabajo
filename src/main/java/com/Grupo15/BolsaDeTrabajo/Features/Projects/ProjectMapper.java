package com.Grupo15.BolsaDeTrabajo.Features.Projects;

import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Projects.dto.ProjectResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    public ProjectEntity toEntity(ProjectRequestDTO requestDTO);

    public ProjectResponseDTO toDto(ProjectEntity project);
}
