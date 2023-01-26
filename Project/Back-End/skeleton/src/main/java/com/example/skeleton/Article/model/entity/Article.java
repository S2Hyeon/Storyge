package com.example.skeleton.Article.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long articleId;

    private String content;

    @Column(name = "user_id")
    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_Id")
    private User user;


    private LocalDateTime createdAt;

    public void updateArticle (String content){
        this.content = content;
    }
}
