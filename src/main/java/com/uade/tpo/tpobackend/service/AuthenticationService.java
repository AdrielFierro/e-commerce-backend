package com.uade.tpo.tpobackend.service;

import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uade.tpo.tpobackend.controllers.auth.AuthenticationRequest;
import com.uade.tpo.tpobackend.controllers.auth.AuthenticationResponse;
import com.uade.tpo.tpobackend.controllers.auth.RegisterRequest;
import com.uade.tpo.tpobackend.controllers.config.JwtService;
import com.uade.tpo.tpobackend.entity.Usuario;
import com.uade.tpo.tpobackend.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
        private final UsuarioRepository repository;
        private final PasswordEncoder passwordEncoder;
        private final JwtService jwtService;
        private final AuthenticationManager authenticationManager;

        public AuthenticationResponse register(RegisterRequest request) {
                var usuario = Usuario.builder()
                                .nombre(request.getNombre())
                                .pass(passwordEncoder.encode(request.getPass()))
                                .role(request.getRole())
                                .build();
                repository.save(usuario);

                var jwtToken = jwtService.generateToken(usuario, usuario.getUsuario_id()); // aca el getusuario_id te da
                                                                                           // el id para crear en el
                                                                                           // token directo del usuario
                                                                                           // recientemente generado
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }

        public AuthenticationResponse authenticate(AuthenticationRequest request) {
                authenticationManager.authenticate(
                                new UsernamePasswordAuthenticationToken(
                                                request.getNombre(),
                                                request.getPass()));

                var usuario = repository.findByNombre(request.getNombre())

                                .orElseThrow();
                var jwtToken = jwtService.generateToken(usuario, usuario.getUsuario_id());
                return AuthenticationResponse.builder()
                                .accessToken(jwtToken)
                                .build();
        }
}
