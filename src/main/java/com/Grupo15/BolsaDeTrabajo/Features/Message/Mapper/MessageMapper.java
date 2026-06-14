package com.Grupo15.BolsaDeTrabajo.Features.Message.Mapper;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.CandidatesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Message.MessageEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Message.dto.MessageResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompaniesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper (componentModel = "spring")
public interface MessageMapper {

    @Mapping(target = "issuerName", expression = "java(mapUserToName(entity.getIssuer()))")
    @Mapping(target = "receptorName", expression = "java(mapUserToName(entity.getReceptor()))") // esto edu lo tiene con expression, pero tmbn se puede hacer con qualified
    MessageResponseDTO toDto(MessageEntity entity);
    MessageEntity toEntity(MessageRequestDTO dto);

    // sin este metodo el candidato que envia un mensaje no tendria apellido.
    @Named("mapUserToName")
    default String mapUserToName(UsersEntity user) {
        if (user == null) return "Unknown User";

        if (user instanceof CandidatesEntity) { // si es un candidato concatenamos el apellido tambien
            CandidatesEntity candidate = (CandidatesEntity) user;
            return candidate.getName() + " " + candidate.getLastName();
        }

        if (user instanceof CompaniesEntity) { // al sacar registeredName solo retornamos el nombre que hereda de user
            return user.getName();
        }

        // sigo usando instanceof aunque no quede opcion pq solo hay dos tipos de usuario para que sea escalable en un posible futuro
        return user.getName();
    }
}
