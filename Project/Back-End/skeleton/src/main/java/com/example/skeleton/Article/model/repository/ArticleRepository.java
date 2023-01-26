package com.example.skeleton.Article.model.repository;

import com.example.skeleton.Article.model.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, ArticleCustomRepository{
    Optional<Article> findAllByContent(String content);
}
