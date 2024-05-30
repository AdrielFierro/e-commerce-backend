package com.uade.tpo.tpobackend.controllers.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
// import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
// import org.springframework.security.web.authentication.logout.LogoutHandler;

import com.uade.tpo.tpobackend.entity.Role;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req.requestMatchers("/api/v1/auth/**").permitAll()
                        .requestMatchers("/error/**").permitAll()

                        .requestMatchers("/usuarios/**", "/ventas/**").hasAnyAuthority(Role.ADMIN.name())
                        // esta linea hace que usuarios y ventas solo pueda ser corrido por admin

                        .requestMatchers("/libros/**", "/categorias/**")
                        .hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        // esta linea indica que para correr libros y categorias tenes que ser user

                        .requestMatchers(HttpMethod.PUT).hasAnyAuthority(Role.USER.name(), Role.ADMIN.name())
                        // para updatear datos si o si hay que estar logueado

                        // en todos los permisos sumo a ADMIN porque quiero que tenga full access
                        .requestMatchers(HttpMethod.GET).permitAll()
                        // la idea con que los get esten permitidos es que sin loguearse, el cliente
                        // pueda ver los libros y categorias que hay.
                        .anyRequest()
                        .authenticated())

                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
