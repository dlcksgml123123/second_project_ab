package com.team5.household.expense.vo.paymentVO;

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
<<<<<<< HEAD:src/main/java/com/team5/household/vo/paymentVO/PaymentAddVO.java
    //    private String message;

=======
    private Integer paymentType;
    private String paymentName;
>>>>>>> c28ebf01235af6604410ef4af3d8bb8fce68ae77:src/main/java/com/team5/household/expense/vo/paymentVO/PaymentAddVO.java
}
