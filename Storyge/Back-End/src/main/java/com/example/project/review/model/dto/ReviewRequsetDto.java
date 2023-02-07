package com.example.project.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewRequsetDto {

    private Long userId;
    private Long diaryId;
    private String reviewContent;

}
