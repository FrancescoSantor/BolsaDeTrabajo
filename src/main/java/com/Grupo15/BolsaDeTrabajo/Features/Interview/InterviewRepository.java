package com.Grupo15.BolsaDeTrabajo.Features.Interview;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InterviewRepository extends JpaRepository<InterviewEntity,Long> {

    boolean existsByApplicationId(Long applicationId);
}
