package com.dfm.Config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.dfm.Users.Entities.UserModel;

@Service
public class JwtService
{
    private static final String SECRET_KEY = "404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970";

    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claims_resolver)
    {
        final Claims claims = extractAllClaims(token);

        return claims_resolver.apply(claims);
    }

    public String generateToken(UserModel user_model)
    {
        return generateToken(new HashMap<>(), user_model);
    }

    public String generateToken(Map<String, Object> extra_claims, UserModel user_model)
    {
        return Jwts
            .builder()
            .setClaims(extra_claims)
            .setSubject(user_model.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
    }

    public boolean isTokenValid(String token, UserModel user_model)
    {
        final var username = extractUsername(token);

        return (username.equals(user_model.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token)
    {
        return extractExpiration(token)
            .before(new Date());
    }

    private Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts
            .parserBuilder()
            .setSigningKey(getSignInKey())
            .build()
            .parseClaimsJws(token)
            .getBody();
    }

    private Key getSignInKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(JwtService.SECRET_KEY);
        
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
