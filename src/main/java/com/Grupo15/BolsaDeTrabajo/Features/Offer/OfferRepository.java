package com.Grupo15.BolsaDeTrabajo.Features.Offer;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface OfferRepository extends JpaRepository<OfferEntity,Long> {

    // Metodo para buscar de forma segura sin exponer el ID numérico
    Optional<OfferEntity> findByExternalId(UUID externalId);

    // RF16: Trae todas las ofertas filtradas por estado (usamos OfferStatus.OPEN para las activas)
    Page<OfferEntity> findAllByOfferStatus(OfferStatus offerStatus, Pageable pageable);

    // RF17: Filtra por el ENUM de título y que el estado sea OPEN al mismo tiempo
    Page<OfferEntity> findByTitleAndOfferStatus(TitleOfOffer titleOfOffer, OfferStatus offerStatus, Pageable pageable);

}

