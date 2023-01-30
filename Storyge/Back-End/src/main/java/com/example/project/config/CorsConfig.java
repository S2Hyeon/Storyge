package com.example.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true); // 자격 증명 허용
        config.addAllowedOrigin("*"); // 외부에서 들어오는 모든 url 허용 -> 주로 api 를 사용하는 곳만 열어둠
        config.addAllowedHeader("*"); // 허용되는 헤더
        config.addAllowedMethod("*"); // 허용되는 메서드

        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

}
