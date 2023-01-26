package com.example.skeleton.Article.model.service;

import com.example.skeleton.Article.model.dto.ArticleDto;
import com.example.skeleton.Article.model.dto.ArticleUpdateParam;
import com.example.skeleton.Article.model.entity.Article;
import com.example.skeleton.Article.model.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService{

    private final ArticleRepository articleRepository;
    @Override
    public void insertArticle(ArticleDto articleDto) {
        articleRepository.save(toEntity(articleDto));
    }

    @Override
    public List<ArticleDto> selectAllArticle() {
        return articleRepository.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public Optional<ArticleDto> selectOneArticle(Long articleId) {
        return Optional.ofNullable(toDto(articleRepository.findById(articleId).orElseThrow()));
    }

    @Override
    public void updateArticle(ArticleUpdateParam param) {
        Article article = articleRepository.findById(param.getArticleId()).orElseThrow();
        article.updateArticle(param.getContent());
    }

    @Override
    public void deleteArticle(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
