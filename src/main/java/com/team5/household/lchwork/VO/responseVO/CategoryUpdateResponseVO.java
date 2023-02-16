package com.team5.household.lchwork.VO.responseVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryUpdateResponseVO {
    @Schema(description = "수정 상태", example = "true")
    private Boolean updated;
    @Schema(description = "메세지", example = "카테고리 정보가 수정되었습니다.")
    private String message;
    @Schema(description = "선택한 카테고리 번호", example = "3")
    private Long no;
    @Schema(description = "수정할 카테고리 이름", example = "영화")
    private String name;
    
}
