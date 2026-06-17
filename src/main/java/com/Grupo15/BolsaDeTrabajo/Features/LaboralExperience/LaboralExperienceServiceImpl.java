package com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidateRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.Mappers.LaboralExperienceMapper;
import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.LaboralExperience.dto.LaboralExperienceResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaboralExperienceServiceImpl implements LaboralExperienceService{

    // Declaración de dependencias requeridas (inmutables a través de final)
    private final ExperienceRepository laboralExperienceRepository; // Repositorio de la tabla de experiencias
    private final CandidateRepository candidatesRepository; // Repositorio de la tabla de candidatos
    private final LaboralExperienceMapper laboralExperienceMapper; // Componente de mapeo automatizado MapStruct

    @Override
    @Transactional
    public LaboralExperienceResponseDTO createExperience(LaboralExperienceRequestDTO requestDto) {

        // Verificamos que el candidato ingresado realmente exista en nuestro sistema
        CandidatesEntity candidate = candidatesRepository.findById(requestDto.candidateId())
                //.orElseThrow(() -> new ResourceNotFoundException("El candidato con ID " + requestDto.candidateId() + " no existe."));

        // Regla de negocio: Validamos que la fecha de fin no sea anterior al inicio si es que fue enviada
        if (requestDto.endDate() != null) {

            // Compara si la fecha de fin se ubica cronológicamente antes del inicio
            if (requestDto.endDate().isBefore(requestDto.initialDate())) {

                //throw new BadRequestException("La fecha de finalización no puede ser previa a la fecha de inicio."); // Detiene el flujo con error 400
            }
        }

        LaboralExperienceEntity entity = laboralExperienceMapper.toEntity(requestDto);

        // Vinculación: Enlazamos el candidato real que recuperamos de la base de datos a la nueva experiencia laboral
        entity.setCandidate(candidate);
        // Persistencia: Guardamos el registro completo en la base de datos
        LaboralExperienceEntity savedEntity = laboralExperienceRepository.save(entity);


        return laboralExperienceMapper.toDto(savedEntity);
    }

    @Override
    @Transactional
    public LaboralExperienceResponseDTO updateExperience(UUID externalId, LaboralExperienceRequestDTO requestDto) { // Recibe el UUID seguro de la experiencia y los nuevos datos

        // Seguridad: Ubicamos la experiencia existente a través de su identificador UUID público
        LaboralExperienceEntity existingExperience = laboralExperienceRepository.findByExternalId(externalId)
                //.orElseThrow(() -> new ResourceNotFoundException("Experiencia laboral no encontrada con el identificador seguro: " + externalId)); // Error 404 si no existe

        // Regla de negocio: Validamos la coherencia de fechas provistas en la petición de cambio
        // Si el JSON editado contiene una fecha de fin
        if (requestDto.endDate() != null) {

            // Verifica si la fecha de fin quebranta la lógica temporal frente al inicio
            if (requestDto.endDate().isBefore(requestDto.initialDate())) {
                //throw new BadRequestException("La fecha de finalización no puede ser previa a la fecha de inicio."); // Lanza error 400 impidiendo la persistencia
            }

        }

        // Seteo manual
        existingExperience.setCompany(requestDto.company());
        existingExperience.setPosition(requestDto.position());
        existingExperience.setInitialDate(requestDto.initialDate());
        existingExperience.setEndDate(requestDto.endDate());
        existingExperience.setDescription(requestDto.description());

        // Guardamos la entidad con los datos modificados directamente en el repositorio
        LaboralExperienceEntity updatedEntity = laboralExperienceRepository.save(existingExperience);

        return laboralExperienceMapper.toDto(updatedEntity);
    }

    @Override
    @Transactional
    // Metodo de eliminación pública mediante UUID
    public void deleteExperience(UUID externalId) {

        // Buscamos la experiencia por su UUID seguro para corroborar su existencia previa
        LaboralExperienceEntity existingExperience = laboralExperienceRepository.findByExternalId(externalId)
                //.orElseThrow(() -> new ResourceNotFoundException("Experiencia laboral no encontrada con el identificador seguro: " + externalId)); // Lanza 404 si no existe

        // Si en este módulo aplican eliminación física directa, borramos el registro por completo
        laboralExperienceRepository.delete(existingExperience);

    }

    @Override
    @Transactional//(readOnly = true) --> tira falla
    // Metodo para ver el detalle de una experiencia sola
    public LaboralExperienceResponseDTO getExperienceByExternalId(UUID externalId) {

        // Buscamos la experiencia por su UUID seguro
        LaboralExperienceEntity experience = laboralExperienceRepository.findByExternalId(externalId)
               // .orElseThrow(() -> new ResourceNotFoundException("Experiencia laboral no encontrada con el identificador seguro: " + externalId)); // Lanza 404 si no coincide

        return laboralExperienceMapper.toDto(experience);
    }

    @Override
    @Transactional//(readOnly = true)
    // Recibe el ID numérico interno del candidato
    public List<LaboralExperienceResponseDTO> getExperiencesByCandidate(Long candidateId) {

        // Validación: Corroboramos si el candidato realmente existe en el sistema
        if (!candidatesRepository.existsById(candidateId)) {
            //throw new ResourceNotFoundException("El candidato con ID " + candidateId + " no existe."); // Frena lanzando un error 404
        }

        // Buscamos todas las experiencias vinculadas al ID del candidato
        List<LaboralExperienceEntity> experiences = laboralExperienceRepository.findAllByCandidateId(candidateId);

        // Convertimos recursivamente la lista de entidades a una lista limpia de DTOs de respuesta
        return experiences.stream()
                .map(laboralExperienceMapper::toDto)
                .collect(Collectors.toList());

    }




}
