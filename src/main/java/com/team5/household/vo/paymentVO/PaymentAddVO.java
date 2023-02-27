package com.team5.household.vo.paymentVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentAddVO {
    @Schema(description = "지출된 방식")
    private Long type;
    @Schema(description = "등록된 결제 수단")
    private String payment;
    @Schema(description = "회원 seq번호")
    private Long memberSeq;
    //    private String message;

}
