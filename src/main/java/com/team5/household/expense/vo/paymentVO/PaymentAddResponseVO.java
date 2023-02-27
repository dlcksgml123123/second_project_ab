package com.team5.household.expense.vo.paymentVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//결제 수단을 추가 기능에 사용
public class PaymentAddResponseVO {
    @Schema(description = "상태설명")
    private Boolean status;
    @Schema(description = "결제된 수단")
    private String type;
    @Schema(description = "결제 상세")
    private String typename;
    @Schema(description = "회원 이메일")
    private String email;
    @Schema(description = "메세지")
    private String message;
}
