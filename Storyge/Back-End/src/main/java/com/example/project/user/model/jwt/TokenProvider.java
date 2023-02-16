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
import java.util.Optional;
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

    //유저 정보를 통해 accesstoken, refreshtoken  생성
    public TokenInfo generateToken(Authentication authentication) {
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        Optional<User> user = userRepository.findByEmail(authorities);
        long now = (new Date()).getTime();

        Date accessTokenExpiresIn = new Date(now + JwtProperties.ACCESS_TOKEN_TIME);
        //accessToken에 userId만 담아서 보냄
        String accessToken = Jwts.builder()
                .setSubject(authentication.getName())
                .claim("userId", user.get().getUserId())
                .claim(JwtProperties.AUTHORITIES_KEY, authorities)
                .setExpiration(accessTokenExpiresIn)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // Refresh Token 생성
        //이미 토큰이 있는 유저라면 있는 토큰 보내주기
        //없다면 생성해서 보내주고 DB 저장
        String refreshToken;
        boolean check;
        Optional<Token> restoreToken = tokenRepository.findByUserId(user.get().getUserId());
        if (restoreToken.isPresent()) {
            refreshToken = String.valueOf(restoreToken);
            check = true;
        } else {
            check = false;
            refreshToken = Jwts.builder()
                    .setExpiration(new Date(now + JwtProperties.REFRESH_TOKEN_TIME))
                    .claim("userId", user.get().getUserId())
                    .signWith(key, SignatureAlgorithm.HS256)
                    .compact();

            //refresh 토큰 DB 저장
            Token token = Token.builder()
                    .userId(user.get().getUserId())
                    .refreshToken(refreshToken)
                    .build();

            tokenRepository.save(token);

        }

        return TokenInfo.builder()
                .grantType(JwtProperties.TOKEN_PREFIX)
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .refreshTokenExpirationTime(JwtProperties.REFRESH_TOKEN_TIME)
                .isUser(check)
                .build();
    }

    // 토큰 복호화해서 정보 꺼내기
    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);

        if (claims.get(JwtProperties.AUTHORITIES_KEY) == null) {
            throw new RuntimeException("권한 정보가 없는 토큰 : AUTHORITIES_KEY가 없음");
        }

        // 권한 가져오기
        Collection<? extends GrantedAuthority> authorities =
                Arrays.stream(claims.get(JwtProperties.AUTHORITIES_KEY).toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());

        User user = userRepository.findById(Long.parseLong(claims.get("userId").toString())).orElseThrow();
        UserDetailCustom principal = new UserDetailCustom(user);

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    //토큰 정보 검증 메서드
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("Invalid JWT Token", e);
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT Token", e);
            //토큰이 유효기간이 다 되었을 경우 재생성해서 보내주기


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
        //access token 남은 시간
        Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().getExpiration();

        //현재 시간
        Long now = new Date().getTime();
        return (expiration.getTime() - now);
    }

}


