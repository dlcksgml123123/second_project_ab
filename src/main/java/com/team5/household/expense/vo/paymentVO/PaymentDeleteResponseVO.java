package com.team5.household.expense.vo.paymentVO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class PaymentDeleteResponseVO {
    @Schema(description = "상태설명")
    private Boolean status;
    @Schema(description = "메세지")
    private String message;
}
