package com.Grupo15.BolsaDeTrabajo.Features.auth;

import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.credentials.CredentialsRepository;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.Role;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.RoleEntity;
import com.Grupo15.BolsaDeTrabajo.Features.auth.permissions.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CredentialsRepository credentialsRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        String adminUsername = "admin";

        if (!credentialsRepository.existsByUsername(adminUsername)) {

            RoleEntity adminRole = roleRepository.findByRole(Role.ROLE_ADMIN)
                    .orElseGet(() -> {
                        RoleEntity newRole = new RoleEntity();
                        newRole.setRole(Role.ROLE_ADMIN);
                        return roleRepository.save(newRole);
                    });

            CredentialsEntity adminCredentials = new CredentialsEntity();
            adminCredentials.setUsername(adminUsername);


            adminCredentials.setPassword(passwordEncoder.encode("admin1234"));
            adminCredentials.setEnabled(true);

            adminCredentials.setRoles(Set.of(adminRole));


            adminCredentials.setUsuario(null);


            credentialsRepository.save(adminCredentials);

            System.out.println("👉 [DataInitializer] Global admin credentials created successfully.");
        } else {
            System.out.println("👉 [DataInitializer] Admin credentials already registered.");
        }
    }
}