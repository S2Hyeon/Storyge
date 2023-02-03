package com.example.project.review.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUpdateParam {

//    private Long diaryId;
    private Long reviewId;
    private String reviewContent;

}
