package com.bci.api.user.security;

import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtTokenProvider {

    private static final String SECRET_KEY = "your_secret_key";
    private static final long EXPIRE_TIME_MS = 3600000; // 1 hour

    public String generateToken() {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + EXPIRE_TIME_MS);

        return Jwts.builder()
                .setSubject("user")
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
}
