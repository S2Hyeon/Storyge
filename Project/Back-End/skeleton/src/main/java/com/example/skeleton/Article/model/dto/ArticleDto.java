package com.example.skeleton.Article.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private Long articleId;

    private String content;

    private Long userId;
    private String nickname;

    private LocalDateTime createdAt;
}
