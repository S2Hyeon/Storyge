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

    Long diaryId;
//    Long userId;
    String nickname;
    String profileImg;

}
