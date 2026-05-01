package com.bootcamp.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    private final String SECRET_KEY = "mi_clave_secreta_super_segura_123456";

    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

    // 🔐 Generar token con roles
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("roles", userDetails.getAuthorities().stream()
                .map(auth -> auth.getAuthority())
                .collect(Collectors.toList()));

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 1 hora
                .signWith(key) // ✅ correcto para jjwt moderno
                .compact();
    }

    // 👤 Extraer username
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // 🎯 Extraer roles
    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        return (List<String>) claims.get("roles");
    }

    // ✅ Validar token
    public boolean validateToken(String token, String username) {
        return username.equals(extractUsername(token)) && !isTokenExpired(token);
    }

    // ⏳ Validar expiración
    public boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

    // 🔍 Extraer claims
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}