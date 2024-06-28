package com.example.basicjwtapp.config;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
public class JwtProvider {

    private static final Logger log = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${app.jwt.secret}")
    private String jwtSecret;

    @Value("${app.jwt.expiration}")
    private long expiration;

    public String generateToken(Authentication authentication){
        String username = authentication.getName();
        Date currentDate = new Date();
        Date expireDate = new Date(currentDate.getTime() + expiration);
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expireDate)
                .signWith(key())
                .compact();
    }

    private Key key(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUsername(String token){
        return Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parseClaimsJwt(token)
                    .getBody()
                    .getSubject();
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key())
                    .build()
                    .parse(token);
            return true;
        } catch (MalformedJwtException exception){
            log.error("jwt token malfunctioned {}", exception.getMessage());
        } catch (ExpiredJwtException exception){
            log.error("jwt token expired {}", exception.getMessage());
        } catch (UnsupportedJwtException exception){
            log.error("unsupported jwt token {}", exception.getMessage());
        } catch (IllegalArgumentException exception){
            log.error("jwt claims string is empty {}", exception.getMessage());
        }
        return false;
    }

    

}
