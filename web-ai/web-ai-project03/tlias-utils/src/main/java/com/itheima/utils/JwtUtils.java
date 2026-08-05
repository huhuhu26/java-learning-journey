package com.itheima.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

public class JwtUtils {
    // 密钥（base64格式）
    private static final String SECRET_KEY = "Z29vZGl1YW9wZW5zb2Z0d2FyZXNlY3JldGtleTEyMzQ1Njc4OQ==";
    // 过期时间：12小时
    private static final long EXPIRATION_TIME = 12 * 60 * 60 * 1000;

    // 将字符串密钥转为加密对象
    private static SecretKey getSecretKey() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 生成JWT令牌
     * @param claims 载荷数据
     * @return jwt字符串
     */
    public static String generateToken(Map<String, Object> claims) {
        SecretKey key = getSecretKey();
        return Jwts.builder()
                .signWith(key, SignatureAlgorithm.HS256)
                .addClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .compact();
    }

    /**
     * 解析JWT令牌
     * @param token jwt令牌
     * @return 载荷Claims
     * @throws Exception 令牌过期/篡改/格式错误抛出异常
     */
    public static Claims parseToken(String token) throws Exception {
        SecretKey key = getSecretKey();
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}

