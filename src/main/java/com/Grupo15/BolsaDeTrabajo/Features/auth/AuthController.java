package com.Grupo15.BolsaDeTrabajo.Features.auth;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Service.CandidateService;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.CompanyServices;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.PerfilEmpresa.dto.CompanyResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.auth.dto.AuthRequest;
import com.Grupo15.BolsaDeTrabajo.Features.auth.dto.AuthResponse;
import com.Grupo15.BolsaDeTrabajo.Features.auth.dto.NewAccountRequest;
import com.Grupo15.BolsaDeTrabajo.Features.auth.jwt.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/BolsaDeTrabajo/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final CandidateService candidateService;
    private final CompanyServices companyService;

    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody
                                                         AuthRequest authRequest){
        UserDetails user = authService.authenticate(authRequest);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register/candidate")
    @ResponseStatus(HttpStatus.CREATED)
    public CandidatesResponseDTO registerCandidate(
            @Valid @RequestBody CandidatesRequestDTO dto) {

        return candidateService.creteCandidate(dto);
    }

    @PostMapping("/register/company")
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponseDTO registerCompany(
           @Valid @RequestBody CompanyNewDTO dto) {

        return companyService.create_Company(dto);
    }
}

