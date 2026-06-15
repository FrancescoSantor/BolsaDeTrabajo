package com.Grupo15.BolsaDeTrabajo.Features.auth;

import com.Grupo15.BolsaDeTrabajo.Features.auth.dto.AuthRequest;
import com.Grupo15.BolsaDeTrabajo.Features.auth.dto.AuthResponse;
import com.Grupo15.BolsaDeTrabajo.Features.auth.dto.NewAccountRequest;
import com.Grupo15.BolsaDeTrabajo.Features.auth.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    private final JwtService jwtService;
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody
                                                         AuthRequest authRequest){
        UserDetails user = authService.authenticate(authRequest);
        String token = jwtService.generateToken(user);
        return ResponseEntity.ok(new AuthResponse(token));
    }
   /* @PostMapping("/register")
    public ResponseEntity<UserDTO> registerUser(@RequestBody
                                                NewAccountRequest newAccountRequest){
        return new ResponseEntity<>(userService.save(newAccountRequest),
                HttpStatus.CREATED);
    }*/
}
