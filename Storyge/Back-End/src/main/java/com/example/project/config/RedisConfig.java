package com.example.project.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@RequiredArgsConstructor
//@EnableRedisRepository
//public class RedisConfig {
//
//    private String host;
//    private int port;
//
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory(){
//        return new LettuceConnectionFactory(host, port);
//    }
//
//    @Bean
//    public RedisTemplate<String, Object> redisTemplate(){
//        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
//        redisTemplate.setConnectionFactory(redisConnectionFactory());
//        redisTemplate.setKeySeriallizer(new StringRedisSerializer());
//        redisTemplate.setValueSerializer(new StringRedisSerializer());
//        return redisTemplate;
//    }
//}
