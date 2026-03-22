package com.iasaweb.cinema.service;

import com.iasaweb.cinema.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import javax.crypto.SecretKey;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;

@Service
public class JwtService {
    private final SecretKey signingKey;

    public JwtService(@Value("${secret}") String secret) {
        System.out.println("SECRET: " + secret);
        signingKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    public String token(User user) {
        ZonedDateTime expirationTime = LocalDateTime.now()
                .atZone(ZoneId.systemDefault())
                .plusHours(24);

        return Jwts.builder()
                .subject(user.getName())
                .claim("userId", Long.toString(user.getId()))
                .expiration(Date.from(expirationTime.toInstant()))
                .signWith(signingKey)
                .compact();
    }

    public String extractUserName(String token) {
        Claims claims = extractPayload(token);
        return claims.getSubject();
    }

    public boolean isExpired(String token) {
        LocalDateTime expirationTime = extractExpiration(token);
        return LocalDateTime.now().isBefore(expirationTime);
    }

    private LocalDateTime extractExpiration(String token) {
        Claims claims = extractPayload(token);
        Date expirationTime = claims.getExpiration();
        return LocalDateTime.ofInstant(expirationTime.toInstant(), ZoneId.systemDefault());
    }

    private Claims extractPayload(String token) {
        JwtParser parser = Jwts.parser().verifyWith(signingKey).build();
        return parser.parseSignedClaims(token).getPayload();
    }
}
