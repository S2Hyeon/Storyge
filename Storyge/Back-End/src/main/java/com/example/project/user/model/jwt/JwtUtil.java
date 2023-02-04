package com.example.project.user.model.jwt;//package com.example.project.user.model.jwt;
//
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//
//public class JwtUtil {
//    public Long getUserId(String token) {
//        token = token.replace(JwtProperties.TOKEN_PREFIX, "");
//        return JWT.require(Algorithm.HMAC512(JwtProperties.SECRET)).build().verify(token)
//                .getClaim("userId").asLong();
//    }
//}
