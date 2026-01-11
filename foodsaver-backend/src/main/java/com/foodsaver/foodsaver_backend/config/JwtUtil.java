//package com.foodsaver.foodsaver_backend.config;
//
//import java.security.Key;
//import java.util.Date;
//
//import io.jsonwebtoken.Claims;
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.security.Keys;
//
//public class JwtUtil {
//
//    private static final Key key =
//            Keys.hmacShaKeyFor("foodsaver-secret-key-very-secure".getBytes());
//
//    private static final long EXPIRATION = 1000 * 60 * 60;
//
//    public static String generateToken(String email, String role) {
//        return Jwts.builder()
//                .setSubject(email)
//                .claim("role", role)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
//                .signWith(key)
//                .compact();
//    }
//
//    public static Claims validateToken(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }
//}
