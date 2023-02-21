package com.team5.household.vo.paymentvo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentResponseVO {
    @Schema(description = "상태설명")
    private Boolean status;
    @Schema(description = "메세지")
    private String message;
    
}
