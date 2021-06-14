package com.example.jwt.jwtwriter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProwider {
    String secret="kalitso'z";
    long expireTime=36_000_000;
    public String generateToken(String username){
        String token = Jwts
                .builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expireTime))
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
                 return token;
    }
    public boolean validateToken(String token){
        try {
            Jwts
                    .parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token);
            return true;
        }catch (Exception s){
            s.printStackTrace();
            return false;
        }
    }
    public String getUsernameFromToken(String token){
        String subject = Jwts
                .parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return subject;
    }
}
