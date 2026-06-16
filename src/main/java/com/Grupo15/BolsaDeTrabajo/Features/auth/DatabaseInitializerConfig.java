package com.Grupo15.BolsaDeTrabajo.Features.auth;

import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.*;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.Roles;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesEntity;
import com.Grupo15.BolsaDeTrabajo.Features.Roles.RolesRepository;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseInitializerConfig {

    @Bean
    @Transactional
    public CommandLineRunner initDatabase(
            PermitRepository permitRepository,
            RoleRepository roleRepository,
            RolesRepository rolesRepository //
    ) {

        return args -> {
            System.out.println("Sincronizando roles viejos de negocio : ");

            // por los roles viejos tuve que hacer esto pq al registrar users me tiraba 500 siempre.
            // Habria que sacar los roles viejos y luego borrar.
            if (rolesRepository.count() == 0) {
                RolesEntity viejoCandidato = new RolesEntity();
                viejoCandidato.setRol(Roles.CANDIDATE);
                rolesRepository.save(viejoCandidato);

                RolesEntity viejaEmpresa = new RolesEntity();
                viejaEmpresa.setRol(Roles.COMPANY);
                rolesRepository.save(viejaEmpresa);
                System.out.println(">> Roles VIEJOS guardados con éxito.");
            }


            if (permitRepository.count() > 0) {
                System.out.println(">> Los permisos ya existían. Evitando duplicados.");
                return;
            }

            System.out.println(">> Cargando permisos y roles nuevos de seguridad...");

            // PERMISOS
            PermitEntity verOfertas = permitRepository.save(PermitEntity.builder().permit(Permits.VER_OFERTAS).build());
            PermitEntity crearOferta = permitRepository.save(PermitEntity.builder().permit(Permits.CREAR_OFERTA).build());
            PermitEntity editarOferta = permitRepository.save(PermitEntity.builder().permit(Permits.EDITAR_OFERTA).build());
            PermitEntity eliminarOferta = permitRepository.save(PermitEntity.builder().permit(Permits.ELIMINAR_OFERTA).build());
            PermitEntity postularseOferta = permitRepository.save(PermitEntity.builder().permit(Permits.POSTULARSE_OFERTA).build());
            PermitEntity verPostulaciones = permitRepository.save(PermitEntity.builder().permit(Permits.VER_POSTULACIONES).build());
            PermitEntity gestionarPostulaciones = permitRepository.save(PermitEntity.builder().permit(Permits.GESTIONAR_POSTULACIONES).build());
            PermitEntity enviarMensaje = permitRepository.save(PermitEntity.builder().permit(Permits.ENVIAR_MENSAJE).build());
            PermitEntity leerMensajes = permitRepository.save(PermitEntity.builder().permit(Permits.LEER_MENSAJES).build());
            PermitEntity administrarSistema = permitRepository.save(PermitEntity.builder().permit(Permits.ADMINISTRAR_SISTEMA).build());

            // ROLE CANDIDATE
            RoleEntity candidateRole = new RoleEntity(Role.ROLE_CANDIDATE);
            candidateRole.getPermits().add(verOfertas);
            candidateRole.getPermits().add(postularseOferta);
            candidateRole.getPermits().add(enviarMensaje);
            candidateRole.getPermits().add(leerMensajes);
            roleRepository.save(candidateRole);

            //ROLE COMPANY
            RoleEntity companyRole = new RoleEntity(Role.ROLE_COMPANY);
            companyRole.getPermits().add(verOfertas);
            companyRole.getPermits().add(crearOferta);
            companyRole.getPermits().add(editarOferta);
            companyRole.getPermits().add(eliminarOferta);
            companyRole.getPermits().add(verPostulaciones);
            companyRole.getPermits().add(gestionarPostulaciones);
            companyRole.getPermits().add(enviarMensaje);
            companyRole.getPermits().add(leerMensajes);
            roleRepository.save(companyRole);

            //ROLE ADMIN
            RoleEntity adminRole = new RoleEntity(Role.ROLE_ADMIN);
            adminRole.getPermits().add(verOfertas);
            adminRole.getPermits().add(crearOferta);
            adminRole.getPermits().add(editarOferta);
            adminRole.getPermits().add(eliminarOferta);
            adminRole.getPermits().add(postularseOferta);
            adminRole.getPermits().add(verPostulaciones);
            adminRole.getPermits().add(gestionarPostulaciones);
            adminRole.getPermits().add(enviarMensaje);
            adminRole.getPermits().add(leerMensajes);
            adminRole.getPermits().add(administrarSistema);
            roleRepository.save(adminRole);

            System.out.println(">> Permisos y roles cargados correctamente");
        };
    }
}