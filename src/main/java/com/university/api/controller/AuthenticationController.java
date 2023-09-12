package com.university.api.controller;

import com.university.api.domain.usuarios.admin.LoginData;
import com.university.api.domain.usuarios.admin.Admin;
import com.university.api.infra.security.JwtTokenDTO;
import com.university.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/login")
public class AuthenticationController {

    // verifica los datos del login y si es el caso retornar un token.
    private final AuthenticationManager authenticationManager;

    // genera un token si los datos de acceso son válidos
    private final TokenService tokenService;


    public AuthenticationController(AuthenticationManager authenticationManager, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }


    /**
     * Recibe datos de usuario y verifica si son válidos.
     * @param loginData datos de usuario que serán verificados.
     * @return token generado por el método generateToken() de la clase TokenService.
     */
    @PostMapping
    public ResponseEntity<JwtTokenDTO> tryLogin(@RequestBody @Valid LoginData loginData){
        var authorizedUser =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginData.username(), loginData.password()));
        var token = tokenService.generateToken((Admin) authorizedUser.getPrincipal());
        System.out.println(token);
        return ResponseEntity.ok(new JwtTokenDTO(token));
    }
}

