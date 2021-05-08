package com.quizhub.person.security;

import com.quizhub.person.model.Person;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Component
public class JwtUtils implements Serializable {

    private static int jwtExpirationMilliseconds;
    private static String jwtSecret;

    @Value("${app.jwtExpiration}")
    public void setJwtExpirationInMs(int jwtExpirationInMs) {
        JwtUtils.jwtExpirationMilliseconds = jwtExpirationInMs;
    }

    @Value("${app.jwtSecret}")
    public void setJwtSecret(String jwtSecret) {
        JwtUtils.jwtSecret = jwtSecret;
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpirationDate(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public Boolean validateToken(String token, PersonDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String generateToken(Person person) {
        return createToken(person.fetchAuthorities(), person.getUsername());
    }

    private String createToken(List<GrantedAuthority> claims, String subject) {
        final long now = System.currentTimeMillis();
        return Jwts.builder()
                .setSubject(subject)
                .claim("authorities", claims/*.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())*/)
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + jwtExpirationMilliseconds))
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = extractExpirationDate(token);
        return expiration.before(new Date());
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
    }
}
