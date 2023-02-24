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
    @Schema(description = "결제 수단 상태값(1:카드/2:계좌/3:현금)")
    private Integer paymentType;
    @Schema(description = "결제 수단의 이름")
    private String paymentName;
    private String message;
}
