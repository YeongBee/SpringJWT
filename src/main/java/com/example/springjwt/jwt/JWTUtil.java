package com.example.springjwt.jwt;

import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JWTUtil {

    private SecretKey secretKey;

    public JWTUtil(@Value("${spring.jwt.secret}")String secret){
        secretKey = new SecretKeySpec(secret.getBytes(
                StandardCharsets.UTF_8), Jwts
                .SIG
                .HS256
                .key()
                .build()
                .getAlgorithm());
    }

    public String getUsername(String token) {

        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("username", String.class);
    }

    public String getRole(String token) {
        return Jwts
                .parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .get("role", String.class);
    }

    public Boolean isExpired(String token) {

        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());
    }

    public String createJwt(String username, String role, long expiredMs ){
        return Jwts.builder()
                .claim("username", username)        // 값 넣기
                .claim("role", role)                // 값 넣기
                .issuedAt(new Date(System.currentTimeMillis())) // 생성된 날짜 확인
                .expiration(new Date(System.currentTimeMillis()+expiredMs)) // 언제 소멸할지 정함
                .signWith(secretKey)                    // 암호화 진행
                .compact();                         // 토큰을 소형화 시킴
    }
}
