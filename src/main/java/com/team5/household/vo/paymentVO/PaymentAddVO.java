package com.team5.household.vo.paymentVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PaymentAddVO {
    @Schema(description = "회원 seq번호")
    private Long memberSeq;
    @Schema(description = "등록된 결제 수단")
    private Long paymentSeq;
//    private String message;

}
