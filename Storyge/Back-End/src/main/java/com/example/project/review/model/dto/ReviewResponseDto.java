package com.example.project.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponseDto {
    Long reviewId;
    Long userId;
    String nickname;
    String profileImg;
    String reviewContent;
    LocalDateTime createdAt;
}
