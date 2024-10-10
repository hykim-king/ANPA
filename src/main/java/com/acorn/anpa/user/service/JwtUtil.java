package com.acorn.anpa.user.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class JwtUtil {
    private static final String SECRET_KEY = "pcwk1212"; // 비밀 키, 실제 환경에서는 더 안전하게 관리해야 합니다.
    private static final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour in milliseconds
    private static final Set<String> BLACKLIST = new HashSet<>(); // 블랙리스트    

    // JWT 생성
    public static String generateToken(String userId, String username) {
        return Jwts.builder()
        		.claim("userId", userId)
        		.claim("username", username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    // JWT에서 클레임 추출
    public static Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    // JWT 유효성 검사
    public static boolean validateToken(String token, String username) {
        final String tokenUsername = getClaimsFromToken(token).getSubject();
        return (tokenUsername.equals(username) && !isTokenExpired(token));
    }

    // JWT 만료 여부 확인
    private static boolean isTokenExpired(String token) {
        final Date expiration = getClaimsFromToken(token).getExpiration();
        return expiration.before(new Date());
    }
    
    // 블랙리스트에 토큰 추가
    public static void blacklistToken(String token) {
        Claims claims = getClaimsFromToken(token);
        BLACKLIST.add(claims.getId());
    }

    // 토큰이 블랙리스트에 있는지 확인
    private static boolean isTokenBlacklisted(String token) {
        Claims claims = getClaimsFromToken(token);
        return BLACKLIST.contains(claims.getId());
    }
}
