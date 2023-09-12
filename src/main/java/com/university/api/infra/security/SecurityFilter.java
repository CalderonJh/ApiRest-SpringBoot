package com.university.api.infra.security;

import com.university.api.domain.usuarios.admin.AdminRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {


    private final TokenService tokenService;

    private final AdminRepository usuarioRepository;


    @Autowired
    public SecurityFilter(TokenService tokenService, AdminRepository usuarioRepository) {
        this.tokenService = tokenService;
        this.usuarioRepository = usuarioRepository;

    }


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    @NotNull HttpServletResponse response,
                                    @NotNull FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if(authHeader != null){
            var token = authHeader.replace("Bearer ", "");
            var subject_name = tokenService.getSubject(token);
            System.out.println(subject_name + " is working!!");
            if (subject_name != null){
                var usuario = usuarioRepository.findByUsername(subject_name);
                var authentication = new UsernamePasswordAuthenticationToken(usuario,
                        null,
                        usuario.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }else {
            System.out.print("\033[31m" + "No se ha declarado un token!\n" + "\u001B[0m");
        }
        filterChain.doFilter(request, response);



    }
}
