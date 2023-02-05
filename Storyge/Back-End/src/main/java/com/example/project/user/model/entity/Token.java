package com.example.project.user.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.naming.Name;
import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@ToString
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tokenId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String refreshToken;
    @Builder
    public Token(Long tokenId, User user, String refreshToken) {
        this.tokenId = tokenId;
        this.user = user;
        this.refreshToken = refreshToken;
    }
}
