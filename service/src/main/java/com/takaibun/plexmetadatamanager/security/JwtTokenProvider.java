package com.takaibun.plexmetadatamanager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JWT Token生成器
 *
 * @author takaibun
 * @since 2024/02/24
 */
@Component
public class JwtTokenProvider {

    /**
     * 根据随机生成的UUID配置secret
     */
    private SecretKey secret;


    @PostConstruct
    public void init() {
        secret = Keys.hmacShaKeyFor(UUID.randomUUID().toString().getBytes());
    }

    public String buildTokenByUsername(String username) {
        return generatorToken(buildClaims(username));
    }

    private Map<String, Object> buildClaims(String username) {
        return new HashMap<>(6) {{
            put("iss", "msm");
            put("sub", username);
            put("exp", generatorExpirationDate());
            put("aud", "internal use");
            put("iat", new Date());
            put("jti", UUID.randomUUID().toString());
        }};
    }


    private String generatorToken(Map<String, Object> claims) {
        return Jwts.builder().claims(claims).signWith(secret, Jwts.SIG.HS256).compact();
    }

    private Date generatorExpirationDate() {
        long expiration = 60 * 60 * 24 * 7L;
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }


    public String getUserNameFromToken(String token) {
        String username;
        try {
            username = getPayloadFromToken(token).getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }


    private Claims getPayloadFromToken(String token) {
        return Jwts.parser().verifyWith(secret).build().parseSignedClaims(token).getPayload();
    }


    public boolean validateToken(String token) {
        return token != null && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return getExpiredDateFromToken(token).before(new Date());
    }


    private Date getExpiredDateFromToken(String token) {
        return getPayloadFromToken(token).getExpiration();
    }

    public String refreshToken(String token) {
        Claims claims = getPayloadFromToken(token);
        Map<String, Object> initClaims = buildClaims(claims.getSubject());
        initClaims.put("iat", new Date());
        return generatorToken(initClaims);
    }
}
