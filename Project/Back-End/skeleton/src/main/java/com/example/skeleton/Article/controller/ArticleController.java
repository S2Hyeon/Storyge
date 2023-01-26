package com.example.skeleton.Article.controller;

import com.example.skeleton.Article.model.dto.ArticleDto;
import com.example.skeleton.Article.model.dto.ArticleUpdateParam;
import com.example.skeleton.Article.model.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class ArticleController {
    private final ArticleService articleService;

    private static final String SUCCESS = "Success";
    private static final String FAIL = "Fail";
    @PostMapping("/article")
    public ResponseEntity<String> insertArticle(ArticleDto articleDto){
        articleService.insertArticle(articleDto);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @GetMapping("/articles")
    public ResponseEntity<List<ArticleDto>> selectAllArticle(){
        return new ResponseEntity<>(articleService.selectAllArticle(), HttpStatus.OK);
    }

    @GetMapping("/article/{articleId}")
    public ResponseEntity<ArticleDto> selectOneArticle(@PathVariable Long articleId){
        return new ResponseEntity<>(articleService.selectOneArticle(articleId).orElseThrow(), HttpStatus.OK);
    }

    @PutMapping("/article")
    public ResponseEntity<String> updateArticle(ArticleUpdateParam param){
        articleService.updateArticle(param);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }

    @DeleteMapping("/article")
    public ResponseEntity<String> deleteArticle(Long articleId){
        articleService.deleteArticle(articleId);
        return new ResponseEntity<>(SUCCESS, HttpStatus.OK);
    }
}
