package com.demo.blogrestapi.security;

import com.demo.blogrestapi.exception.BlogException;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {
    @Value("${app.jwt-secret}")
    private String jwtSecret;

    @Value("${app.jwt-expiration-milliseconds}")
    private Integer jwtExpirationInMs;

    public String generateToken (Authentication authentication) {
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expiryDate = new Date(currentDate.getTime() + jwtExpirationInMs);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwt (String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public Boolean validateToken (String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);

            return true;
        } catch (SignatureException exception){
            throw new BlogException("Invalid JWT signature");
        } catch (MalformedJwtException exception) {
            throw new BlogException("Invalid JWT token");
        } catch (ExpiredJwtException exception) {
            throw new BlogException("Expired JWT token");
        } catch (UnsupportedJwtException exception) {
            throw new BlogException("Unsupported JWT token");
        } catch (IllegalArgumentException exception) {
            throw new BlogException("JWT claims string is empty.");
        }
    }
}
