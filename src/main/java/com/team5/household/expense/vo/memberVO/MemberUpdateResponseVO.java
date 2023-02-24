package com.team5.household.expense.vo.memberVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberUpdateResponseVO {
    @Schema(description = "상태설명")
    private Boolean status;
    @Schema(description = "메세지")
    private String message;
    MemberUpdateVO data;
}
