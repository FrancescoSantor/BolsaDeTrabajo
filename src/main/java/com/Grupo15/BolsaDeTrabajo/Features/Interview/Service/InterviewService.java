package com.Grupo15.BolsaDeTrabajo.Features.Interview.Service;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
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

        if (interviewRepository.existsByApplicationExternalId(interviewRequestDTO.externalId())) {
            throw new IllegalStateException("This application already has an assigned interview.");
        }

        PostulationsEntity application = postulationsRepository.findByExternalId(interviewRequestDTO.externalId())
                .orElseThrow(() -> new ElementNotFoundException("Application not found." ));

        InterviewEntity interviewEntity = interviewMapper.toEntity(interviewRequestDTO, application);

        interviewEntity.setApplication(application);

        InterviewEntity savedInterview = interviewRepository.save(interviewEntity);

        return interviewMapper.toResponse(savedInterview);
    }

    // Obetener en base al id
    @Transactional
    public InterviewResponseDTO getInterviewById(Long id) {

        InterviewEntity interview = interviewRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Interview not found."));

        return interviewMapper.toResponse(interview);
    }

    // actualizar completo
    @Transactional
    public InterviewResponseDTO updateStatus(Long id, InterviewStatus newStatus) {
        InterviewEntity interview = interviewRepository.findById(id)
                .orElseThrow(() -> new ElementNotFoundException("Interview not found."));

        interview.setStatus(newStatus);
        InterviewEntity updatedInterview = interviewRepository.save(interview);

        return interviewMapper.toResponse(updatedInterview);
    }

    // si se elimina se elimina, no tienen para solo cambiarle el estado
    @Transactional
    public void deleteInterview(Long id) {
        if (!interviewRepository.existsById(id)) {
            throw new ElementNotFoundException("Interview not found.");
        }
        interviewRepository.deleteById(id);
    }
}
