package com.example.project.follow.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "follow 관련 기능 실행시 필요한 상대방 정보", description = "상대방의 userId(pk)")
public class UserIdDto {
    @ApiModelProperty(value="사용자 userId(pk)",notes = "사용자 userId(pk값)-상대방",example = "20")
    private Long userId;
}
