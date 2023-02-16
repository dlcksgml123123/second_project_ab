package com.team5.household.lchwork.VO.responseVO;

import java.util.List;

import com.team5.household.lchwork.entity.LchCultureDetailCategoryEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailCategoryListResponseVO {
    @Schema(description = "상태", example = "true")
    private Boolean status; 
    @Schema(description = "메세지", example = "카테고리 리스트 조회에 성공했습니다.")
    private String message; 
    @Schema(description = "추가된 소분류 카테고리 정보")
    private List<LchCultureDetailCategoryEntity> cdclist;
}
