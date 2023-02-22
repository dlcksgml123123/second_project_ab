package com.team5.household.vo.memberVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
//회원정보 삭제할 때 사용
public class UserResponseVO {
    @Schema(description = "상태설명")
    private Boolean status;
    @Schema(description = "메세지")
    private String message;
}
