package com.Grupo15.BolsaDeTrabajo.Features.auth;

import com.Grupo15.BolsaDeTrabajo.Features.Candidate.Service.CandidateService;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesRequestDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Candidate.dto.CandidatesResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Company.CompanyServices;
import com.Grupo15.BolsaDeTrabajo.Features.Company.dto.CompanyNewDTO;
import com.Grupo15.BolsaDeTrabajo.Features.Company.dto.CompanyResponseDTO;
import com.Grupo15.BolsaDeTrabajo.Features.auth.dto.AuthRequest;
import com.Grupo15.BolsaDeTrabajo.Features.auth.dto.AuthResponse;
import com.Grupo15.BolsaDeTrabajo.Features.auth.jwt.JwtService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/BolsaDeTrabajo/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = "Endpoints for user login and registration of candidate or company accounts")
public class AuthController {
    private final AuthService authService;
    private final CandidateService candidateService;
    private final CompanyServices companyService;

    private final JwtService jwtService;
    @PostMapping("/login")
    @Operation(summary = "login", description = "Authenticates user credentials and returns a secure JWT token for subsequent requests.")
    @ApiResponse(responseCode = "200", description = "Successfully authenticated and token generated")
    @ApiResponse(responseCode = "401", description = "Invalid username or password")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody
                                                         AuthRequest authRequest){
        UserDetails user = authService.authenticate(authRequest);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/register/candidate")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new candidate", description = "Allows creating a new user account with the candidate profile role.")
    @ApiResponse(responseCode = "201", description = "Candidate registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid candidate validation payload")
    @ApiResponse(responseCode = "409", description = "Candidate credentials or email already registered")
    public CandidatesResponseDTO registerCandidate(
            @Valid @RequestBody CandidatesRequestDTO dto) {

        return candidateService.creteCandidate(dto);
    }

    @PostMapping("/register/company")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Register a new company", description = "Allows creating a new user account with the company profile role.")
    @ApiResponse(responseCode = "201", description = "Company registered successfully")
    @ApiResponse(responseCode = "400", description = "Invalid company validation payload")
    @ApiResponse(responseCode = "409", description = "Company CUIT, username or email already registered")
    public CompanyResponseDTO registerCompany(
           @Valid @RequestBody CompanyNewDTO dto) {

        return companyService.create_Company(dto);
    }
}

