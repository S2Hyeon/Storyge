package com.example.skeleton.config;

import com.example.skeleton.Article.model.repository.ArticleRepository;
import com.example.skeleton.Article.model.service.ArticleService;
import com.example.skeleton.Article.model.service.ArticleServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {

    private final ArticleRepository articleRepository;

    @Bean
    public ArticleService articleService(){return new ArticleServiceImpl(articleRepository);
    }

}
