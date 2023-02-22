package com.team5.household.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PaymentAddVO {
    @Schema(description = "상태값으로 값을 입력합니다.(1:카드/2:계좌/3:현금)")
    private Integer paymentType;
    @Schema(description = "등록할 결제 수단의 이름을 입력합니다.")
    private String paymentName;

}
