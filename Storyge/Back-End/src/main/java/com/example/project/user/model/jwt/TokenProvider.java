package com.example.project.user.model.jwt;

import com.example.project.user.model.entity.Token;
import com.example.project.user.model.entity.User;
import com.example.project.user.model.oauth.UserDetailCustom;
import com.example.project.user.model.repository.TokenRepository;
import com.example.project.user.model.repository.UserRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class TokenProvider {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final Key key;

    public TokenProvider(UserRepository userRepository, TokenRepository tokenRepository) {
        byte[] keyBytes = Decoders.BASE64.decode(JwtProperties.SECRET);
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    public TokenInfo generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        System.out.println("===================================================================================");
        System.out.println("authorities: "+authorities);
        System.out.println("===================================================================================");
        System.out.println("TokenProvider의 authentication.getName(): "+authentication.getName());
        System.out.println("===================================================================================");
        System.out.println("TokenProvider의 authentication.getPrincipal(): "+authentication.getPrincipal());
        System.out.println("===================================================================================");
        System.out.println("TokenProvider의 authentication.getDetails(): "+authentication.getDetails());
        System.out.println("===================================================================================");
        User user = userRepository.findByEmail(authorities).orElseThrow();
        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + JwtProperties.ACCESS_TOKEN_TIME);
        //accessToken에 userId만 담아서 보냄
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("id", user.getUserId())
//                .claim("name", user.getNickname())
                .claim(JwtProperties.AUTHORITIES_KEY, authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setExpiration(new Date(now + JwtProperties.REFRESH_TOKEN_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        Token token = Token.builder()
                .user(user)
                .refreshToken(refreshToken)
                .build();
        //refresh token 저장
        tokenRepository.save(token);

        return TokenInfo.builder()
                .grantType(JwtProperties.TOKEN_PREFIX)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(JwtProperties.REFRESH_TOKEN_TIME)
                .build();
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get(JwtProperties.AUTHORITIES_KEY) == null) {
            throw new RuntimeException("XX");
        }

        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get("auth").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User user = userRepository.findById(Long.parseLong(claims.get("id").toString())).orElseThrow();
        UserDetailCustom principal = new UserDetailCustom(user);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT Token", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT claims string is empty.", e);
        }
        return false;
    }

    private Claims parseClaims(String accessToken) {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        }
    }

    public Long getExpiration(String accessToken) {
        Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().getExpiration();
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

    public Long getUserId(String accessToken) {
        return Long.getLong(parseClaims(accessToken).get("id").toString());
    }
}


