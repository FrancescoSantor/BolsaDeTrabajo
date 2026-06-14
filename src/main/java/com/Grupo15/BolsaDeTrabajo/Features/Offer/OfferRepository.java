package com.Grupo15.BolsaDeTrabajo.Features.Offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface OfferRepository extends JpaRepository<OfferEntity,Long> {

    // Metodo para buscar de forma segura sin exponer el ID numérico
    Optional<OfferEntity> findByExternalId(UUID externalId);

    // RF16: Trae todas las ofertas filtradas por estado (usamos OfferStatus.OPEN para las activas)
    Page<OfferEntity> findAllByStatus(OfferStatus offerStatus, Pageable pageable);

    // RF17: Filtra por el ENUM de título y que el estado sea OPEN al mismo tiempo
    Page<OfferEntity> findByTitleAndStatus(TitleOfOffer titleOfOffer, OfferStatus offerStatus, Pageable pageable);
}

