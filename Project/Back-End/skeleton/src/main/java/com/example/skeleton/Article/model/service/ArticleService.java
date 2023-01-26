package com.example.skeleton.Article.model.service;

import com.example.skeleton.Article.model.dto.ArticleDto;
import com.example.skeleton.Article.model.dto.ArticleUpdateParam;
import com.example.skeleton.Article.model.entity.Article;

import java.util.List;
import java.util.Optional;

public interface ArticleService {

    //C
    void insertArticle(ArticleDto articleDto);

    //R
    List<ArticleDto> selectAllArticle();

    Optional<ArticleDto> selectOneArticle(Long articleId);

    //U
    void updateArticle (ArticleUpdateParam param);

    //D
    void deleteArticle(Long articleId);

    // DB-> 서버
    default ArticleDto toDto(Article article){
        return new ArticleDto().builder()
                .articleId(article.getArticleId())
                .content(article.getContent())
                .userId(article.getUser().getUserId())
                .nickname(article.getUser().getNickName())
                .createdAt(article.getCreatedAt())
                .build();
    }

    //서버 -> DB
    default Article toEntity(ArticleDto articleDto){
        return new Article().builder()
                .content(articleDto.getContent())
                .userId(articleDto.getUserId())
                .build();
    }
}
