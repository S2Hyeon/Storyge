package com.example.project.recentdiary.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecentDiaryResponseDto {

    private Long diaryId;
//    Long userId;
    private String nickname;
    private String profileImg;

}
