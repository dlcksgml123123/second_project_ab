package com.team5.household.lchwork.VO;

import com.team5.household.lchwork.entity.LchCultureCategoryEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryInputResponseVO {
    @Schema(description = "상태", example = "true")
    private Boolean status;
    @Schema(description = "메세지", example = "카테고리가 추가되었습니다.")
    private String message;
    @Schema(description = "추가된 대분류 카테고리 정보")
    private LchCultureCategoryEntity ccEntity;
}
