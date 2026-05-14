package com.Grupo15.BolsaDeTrabajo.Features.Users.Mapper;

//import com.Grupo15.BolsaDeTrabajo.Features.Users.UsersEntity;
//import com.Grupo15.BolsaDeTrabajo.Features.Users.dto.UsersRequestDTO;
//import com.Grupo15.BolsaDeTrabajo.Features.Users.dto.UsersResponseDTO;
//import lombok.Builder;
//import lombok.Data;
//
//
//public class UsersMapper {
//
//    //Entity --> Response (Cuando Devolves Datos)
//    public static UsersResponseDTO toResponse(UsersEntity entity) {
//        return UsersResponseDTO.builder()
//                .externalId(entity.getExternalId())
//                .name(entity.getName())
//                .lastName(entity.getLastName())
//                .email(entity.getEmail())
//                .active(entity.isActive())
//                .rol(entity.getRol().getRol())
//                .createdAt(entity.getCreatedAt())
//                .build();
//    }
//
//    public static UsersEntity toEntity(UsersRequestDTO request) {
//        return UsersEntity.builder()
//                .name(request.name())
//                .lastName(request.lastName())
//                .email(request.email())
//                .password(request.password())
//                .build();
//    }
//
//}

