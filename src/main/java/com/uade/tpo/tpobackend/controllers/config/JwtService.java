package com.uade.tpo.tpobackend.controllers.config;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;
import org.springframework.stereotype.Service;

import com.uade.tpo.tpobackend.repository.UsuarioRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

@Service
public class JwtService {
    @Value("${application.security.jwt.secretKey}")
    private String secretKey;
    @Value("${application.security.jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(
            UserDetails userDetails, int userid) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", userid);

        return buildToken(userDetails, jwtExpiration, userid);
    }

    private String buildToken(
            UserDetails userDetails,
            long expiration, int userid) {
        // cuando creo el token le pido que me pase los userdetails, la expiracion y le
        // paso el user id(no se lo pide al cliente lo toma directo cuando crea el user)
        return Jwts
                .builder()
                .subject(userDetails.getUsername()) // me va a traer el nombre del user
                .issuedAt(new Date(System.currentTimeMillis()))
                .claim("id", userid) // este campo es el añadido al token es el id del usuario
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractClaim(token, Claims::getSubject);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public String extractId(String token) {
        return extractClaim(token, Claims::getSubject);
    }
    // aca extraigo el id supuestamente

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSecretKey() {
        SecretKey secretKeySpec = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return secretKeySpec;
    }
}
