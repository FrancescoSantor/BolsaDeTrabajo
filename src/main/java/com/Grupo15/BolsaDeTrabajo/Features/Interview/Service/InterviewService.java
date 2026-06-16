package com.Grupo15.BolsaDeTrabajo.Features.Interview.Service;

import com.Grupo15.BolsaDeTrabajo.Features.Interview.InterviewEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.InterviewRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.InterviewStatus;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.Mapper.InterviewMapper;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Interview.dto.InterviewResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Postulacion.PostulationsEntity;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InterviewService {

    private final InterviewRepository interviewRepository;
    private final PostulationRepository postulationsRepository;
    private final InterviewMapper interviewMapper;

    @Transactional
    public InterviewResponseDTO createInterview(InterviewRequestDTO interviewRequestDTO) {

        if (interviewRepository.existsByApplicationId(interviewRequestDTO.applicationId())) {
            throw new IllegalStateException("Esta postulación ya tiene una entrevista asignada.");
        }

        PostulationsEntity application = postulationsRepository.findById(interviewRequestDTO.applicationId())
                .orElseThrow(() -> new EntityNotFoundException("Postulación no encontrada" ));

        InterviewEntity interviewEntity = interviewMapper.toEntity(interviewRequestDTO, application);
        InterviewEntity savedInterview = interviewRepository.save(interviewEntity);

        return interviewMapper.toResponse(savedInterview);
    }

    // Obetener en base al id
    @Transactional
    public InterviewResponseDTO getInterviewById(Long id) {

        InterviewEntity interview = interviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrevista no encontrada"));

        return interviewMapper.toResponse(interview);
    }

    // actualizar completo
    @Transactional
    public InterviewResponseDTO updateStatus(Long id, InterviewStatus newStatus) {
        InterviewEntity interview = interviewRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entrevista no encontrada"));

        interview.setStatus(newStatus);
        InterviewEntity updatedInterview = interviewRepository.save(interview);

        return interviewMapper.toResponse(updatedInterview);
    }

    // si se elimina se elimina, no tienen para solo cambiarle el estado
    @Transactional
    public void deleteInterview(Long id) {
        if (!interviewRepository.existsById(id)) {
            throw new EntityNotFoundException("Entrevista no encontrada");
        }
        interviewRepository.deleteById(id);
    }
}
