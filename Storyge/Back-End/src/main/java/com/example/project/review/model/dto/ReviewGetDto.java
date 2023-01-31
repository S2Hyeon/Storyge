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
public class ReviewGetDto {
    Long reviewId;
    String nickname;
    String reviewContent;
    LocalDateTime createdAt;
}
