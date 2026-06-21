package com.Grupo15.BolsaDeTrabajo.Features.Offer;

import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.BussinesRulesException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.ElementNotFoundException;
import com.Grupo15.BolsaDeTrabajo.Features.CommonsFeatures.Exceptions.InvalidSalaryRangeException;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.Mapper.OfferMapper;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompanyRepository;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Offer.dto.OfferResponseDTO;
//import com.Grupo15.BolsaDeTrabajo.Exception.ResourceNotFoundException;
//import com.Grupo15.BolsaDeTrabajo.Exception.BadRequestException;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final CompanyRepository companiesRepository;
    private final OfferMapper offerMapper;
    private final CredentialsRepository credentialsRepository;

    @Override
    @Transactional//Crear Oferta en el sistema
    public OfferResponseDTO createOffer(OfferRequestDTO requestDto) {

        // Validación: Chequeamos que el ID de la empresa no sea nulo
        if (requestDto.companyId() == null) {
                                            //El ID de la empresa es obligatorio.
            throw new BussinesRulesException("Company ID is required.");
        }

        // Buscamos la empresa en la base de datos para verificar que realmente exista
        CompaniesEntity company = companiesRepository.findById(requestDto.companyId())
                                                                //La empresa con ID " + requestDto.companyId() + " no existe.
                .orElseThrow(() -> new ElementNotFoundException("Company with ID " + requestDto.companyId() + " does not exist."));

        // Validación: El título de la oferta (Enum) no puede ser nulo
        if (requestDto.title() == null) {
                                            //"El título de la oferta es obligatorio."
            throw new BussinesRulesException("Offer title is required.");
        }

        // Regla de negocio: Validamos que los rangos salariales sean coherentes entre sí
        if (requestDto.minSalary() != null && requestDto.maxSalary() != null) {
            if (requestDto.minSalary() > requestDto.maxSalary()) {
                                           //El salario mínimo no puede ser mayor al salario máximo.
                throw new InvalidSalaryRangeException("Minimum salary cannot be greater than maximum salary.");
            }
        }

        // Conversión de datos: Transformamos el Record que nos mandaron en una entidad JPA lista para persistir
        OfferEntity entity = offerMapper.toEntity(requestDto);

        // Asociación de relaciones: Vinculamos la empresa que encontramos al objeto de la oferta laboral
        entity.setCompany(company);

        //RNF12 - Baja lógica: Forzamos el estado inicial de la oferta como abierta
        entity.setOfferStatus(OfferStatus.OPEN); // Nace activa

        if (requestDto.location() == null || requestDto.location().isBlank()) {
            entity.setLocation(company.getLocation());
        }

        // Persistencia: Guardamos la entidad con todos sus datos en la base de datos
        OfferEntity savedEntity = offerRepository.save(entity);

        //Convertimos la entidad guardada en el DTO seguro que espera recibir el frontend
        return offerMapper.toDto(savedEntity);
    }


    @Override
    @Transactional // Update/Actualizacion de oferta
    public OfferResponseDTO updateOffer(UUID externalId, OfferRequestDTO requestDto, Authentication authentication) {

        // Buscamos la oferta existente en la base de datos usando su UUID seguro
        OfferEntity existingOffer = offerRepository.findByExternalId(externalId)
                                                            // "Oferta laboral no encontrada con el ID seguro: "
                .orElseThrow(() -> new ElementNotFoundException("Job offer not found with the secure identifier: " + externalId));

        // Verificamos si la oferta ya fue cerrada previamente (baja lógica)
        if (existingOffer.getOfferStatus() == OfferStatus.CLOSE) {
                                            // "No se puede modificar una oferta laboral que ha sido dada de baja."
            throw new BussinesRulesException("Cannot modify a job offer that has already been closed.");
        }

        // Comprobamos si el usuario actual tiene permisos sobre esta oferta
        checkOfferAuthority(existingOffer, authentication);

        // El título de la oferta modificado no puede venir vacío
        if (requestDto.title() == null) {
                                         // "El título de la oferta no puede ser nulo"
            throw new BussinesRulesException("Offer title cannot be null");
        }

        // Validamos la coherencia de los montos salariales editados
        if (requestDto.minSalary() != null && requestDto.maxSalary() != null) {
            // Si el sueldo mínimo supera al máximo, lanzamos la excepción específica
            if (requestDto.minSalary() > requestDto.maxSalary()) {
                                                   // "El salario mínimo no puede ser mayor al salario máximo."
                throw new InvalidSalaryRangeException("Minimum salary cannot be greater than maximum salary.");
            }
        }

        // Seteo manual de los nuevos campos provenientes del DTO
        existingOffer.setTitle(requestDto.title());
        existingOffer.setDescription(requestDto.description());
        existingOffer.setModality(requestDto.modality());
        existingOffer.setContractType(requestDto.contractType());
        existingOffer.setMinSalary(requestDto.minSalary());
        existingOffer.setMaxSalary(requestDto.maxSalary());

        // Guardamos la entidad actualizada con los nuevos cambios en la base de datos
        OfferEntity updatedEntity = offerRepository.save(existingOffer);

        return offerMapper.toDto(updatedEntity);
    }

    @Override
    @Transactional // Eliminar/Dar de baja una oferta
    public void deleteOffer(UUID externalId, Authentication authentication) {

        // Buscamos la oferta en el repositorio para validar que exista antes de darla de baja
        OfferEntity existingOffer = offerRepository.findByExternalId(externalId)
                                                            // "Oferta laboral no encontrada con el ID seguro: "
                .orElseThrow(() -> new ElementNotFoundException("Job offer not found with the secure identifier: " + externalId));

        // Si la oferta ya estaba en estado CLOSE, evitamos procesarla de nuevo
        if (existingOffer.getOfferStatus() == OfferStatus.CLOSE) {
                                         // "La oferta laboral ya se encuentra dada de baja (CLOSE)."
            throw new BussinesRulesException("The job offer is already closed.");
        }

        // Verificamos si es ADMIN o el dueño real de la oferta
        checkOfferAuthority(existingOffer, authentication);

        // Aplicamos la baja lógica cambiando el estado de la oferta a CLOSE
        existingOffer.setOfferStatus(OfferStatus.CLOSE);

        offerRepository.save(existingOffer);
    }

    // Metodo privado auxiliar para validar los permisos de edición/eliminación
    private void checkOfferAuthority(OfferEntity offer, Authentication authentication) {

        // Evaluamos si el usuario conectado posee el rol de Administrador global
        boolean isAdmin = authentication.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));

        // Si es ADMIN, salteamos el resto de los controles y permitimos la acción directo
        if (isAdmin) {
            return;
        }

        // Si no es admin, obtenemos el username (email/usuario) de la sesión activa
        String username = authentication.getName();

        // Buscamos las credenciales del usuario en la base de datos para recuperar su entidad completa
        CredentialsEntity credentials = credentialsRepository.findByUsername(username)
                                                                // "Usuario autenticado no encontrado"
                .orElseThrow(() -> new ElementNotFoundException("Authenticated user not found"));

        // Obtenemos la entidad de usuario asociada a esas credenciales de inicio de sesión
        UsersEntity loggedUser = credentials.getUsuario();

        // Comparamos el ID de la empresa dueña de la oferta con el ID del usuario de la sesión
        if (!offer.getCompany().getId().equals(loggedUser.getId())) {
                                            // "No tienes permiso para gestionar esta oferta"
            throw new BussinesRulesException("You do not have permission to manage this offer");
        }
    }

    @Override
    @Transactional(readOnly = true)//Listar ofertas por ID
    public OfferResponseDTO getOfferById(UUID externalId) {
        // Buscamos usando el UUID seguro
        OfferEntity offer = offerRepository.findByExternalId(externalId)
                                                             //Oferta laboral no encontrada con el ID seguro: " + externalId
                .orElseThrow(() -> new ElementNotFoundException("Job offer not found with the secure identifier: " + externalId));

        // Si la oferta está dada de baja lógicamente, protegemos el dato simulando que no existe
        if (offer.getOfferStatus() == OfferStatus.CLOSE) {
                                            //La oferta laboral solicitada ya no está disponible
            throw new ElementNotFoundException("The requested job offer is no longer available.");
        }

        return offerMapper.toDto(offer);
    }

    @Override
    @Transactional(readOnly = true) //Listar Ofertas
    public Page<OfferResponseDTO> getOffers(Pageable pageable, TitleOfOffer titleOfOfferEnum) {

        // Variable local que contendrá la página de entidades resultado de la base de datos
        Page<OfferEntity> offersPage;

        // RF17: Evaluamos si el usuario envió un filtro por título específico (Enum)
        if (titleOfOfferEnum != null) {
            // RNF12 y RF17: Buscamos de forma paginada aplicando el filtro de título Y asegurando que el estado sea estrictamente OPEN
            offersPage = offerRepository.findByTitleAndOfferStatus(titleOfOfferEnum, OfferStatus.OPEN, pageable);
        } else {
            // RF16: Traemos el listado completo paginado, pero filtrando únicamente que estén activas (status = OPEN)
            offersPage = offerRepository.findAllByOfferStatus(OfferStatus.OPEN, pageable);
        }

        return offersPage.map(offerMapper::toDto);
    }

}