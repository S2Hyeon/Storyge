package com.example.project.user.model.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import static com.example.project.user.model.jwt.JwtProperties.SECRET;

public class JwtUtil {
    public Long getUserId(String token) {
        System.out.println("JwtUtil");
        System.out.println("token :" + token);
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
        System.out.println("replaced: " + token);

        Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        System.out.println("claims: " + claims);
        Long userId = claims.getBody().get("userId", Long.class);

        System.out.println(userId);
        System.out.println("============================================================================");

        return userId;
    }
}
