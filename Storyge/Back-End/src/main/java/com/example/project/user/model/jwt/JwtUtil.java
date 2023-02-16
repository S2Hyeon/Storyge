package com.example.project.user.model.jwt;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import static com.example.project.user.model.jwt.JwtProperties.SECRET;

public class JwtUtil {

    public Long getUserId(String token) {
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");

        Jws<Claims> claims = Jwts.parserBuilder().setSigningKey(SECRET).build().parseClaimsJws(token);

        return claims.getBody().get("userId", Long.class);
    }
}
