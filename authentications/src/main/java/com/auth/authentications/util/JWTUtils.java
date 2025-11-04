package com.auth.authentications.util;

import com.auth.authentications.Models.DTO.TokenUserResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.SecretKey;

@Component
public class JWTUtils {

    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
    private static final String SECRET = "GpwEjc0vvHsjtisOgFIZ2tN7MZz2piuEM3QMKIdZ6Oo=";

    private SecretKey getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String generateToken(long id, String username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", id);
        claims.put("username", username);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Map<String, Object> decodeToken(String token) {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET); // same key used for signing
        SecretKey key = Keys.hmacShaKeyFor(keyBytes);

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        Map<String, Object> extracted = new HashMap<>();
        extracted.put("userId", claims.get("userId"));
        extracted.put("username", claims.get("username"));
        return extracted;
    }
    public TokenUserResponse getTokenUser(String token) {
        Map<String, Object> map = decodeToken(token);
        long id = ((Number) map.get("userId")).longValue();
        String username = (String) map.get("username");
        return new TokenUserResponse(id, username);
    }

}