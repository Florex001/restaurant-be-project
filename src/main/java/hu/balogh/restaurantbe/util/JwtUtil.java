package hu.balogh.restaurantbe.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private String SECRET = "kslQZHAOMVvtRr8Omol6iMbqlKkBURtrxA6IvnWU81J+s0trpEcNLg2COI5e2y2t1IONUau7sUbEp7Y69gF9VdPH4+ni9DWH5IcQQ7hqp1U6PaaNH77DknnbnwJj5LKWFbiQlBRzP6RuHA3cavjBqFvwQw8hVBz0k8g9rbA3Lfr+I692/fBkAZwFWIDWK/5B+euinW+K4xc0wBqDpN4hQwTDMzrl9S/AjdEXCIx8MfC3tYHN5EzTo+zMzNecCcP8HXhro29l/TJoA53AkGJndDEWZBdaiwCsEuLKOTdSNRohFLxk6crt2/QD/tLFRVLY";

    public String generateToken(String email){
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);

    }

    private String createToken(Map<String, Object> claims, String email) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
                .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
    }

    private Key getSignKey() {
        byte[] keyBites = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBites);
    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolvers) {
        final Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);

    }

    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    private Claims extractAllClaims(String token){
        return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();
    }

    private boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());
    }



}
