package com.university.api.controller;

import com.university.api.domain.usuarios.UserAuthentication;
import com.university.api.domain.usuarios.Usuario;
import com.university.api.infra.security.JwtTokenDato;
import com.university.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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

    private final AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    public AuthenticationController(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }


    @PostMapping
    public ResponseEntity<JwtTokenDato> login(@RequestBody @Valid UserAuthentication u){
//        Authentication authentication = new UsernamePasswordAuthenticationToken(u.username(), u.password());

        var authorizedUser =  authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(u.username(), u.password()));
        var token = tokenService.generateToken((Usuario) authorizedUser.getPrincipal());

        return ResponseEntity.ok(new JwtTokenDato(token));
    }
}
