package com.example.project.user.model.jwt;


import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

public class JwtUtil {
    public Long getUserId(String token) {
        token = token.replace(JwtProperties.TOKEN_PREFIX, "");

        System.out.println("token : "+ token);

        Long userId = JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                .getClaim("userId").asLong();
        System.out.println("userId : " + userId);
//        Jws<Claims> claims = Jwts.parser().setSigningKey(JwtProperties.SECRET).parseClaimsJws(token);
//        System.out.println("claims : "+ claims);
//        Claims payload = claims.getBody();
//        System.out.println("payload : " + payload.get("userId"));

        return JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
                .getClaim("userId").asLong();

    }
}
