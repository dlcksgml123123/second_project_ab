package com.team5.household.expense.vo.paymentVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PaymentAddVO {
    @Schema(description = "회원 seq번호")
    private Long memberSeq;
    private Integer paymentType;
    private String paymentName;
}
