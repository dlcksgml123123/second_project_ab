package com.team5.household.lchwork.VO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailCategoryDeleteResponseVO {
    @Schema(description = "메세지", example = "카테고리 정보가 삭제되었습니다.")
    private String message;
}
