package com.example.project.user.model.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Columns;

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

    @Column(name = "user_id")
    private Long userId;
    @OneToOne
    @JoinColumn(name = "user_id", updatable = false, insertable = false)
    private User user;

    private String refreshToken;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
    @Builder
    public Token(Long tokenId, Long userId, String refreshToken) {
        this.tokenId = tokenId;
        this.userId = userId;
        this.refreshToken = refreshToken;
    }
}
