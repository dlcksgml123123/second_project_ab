package com.team5.household.vo.paymentVO;

import java.util.List;

import com.team5.household.entity.PaymentInfoEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PaymentListVO {
    @Schema(description = "상태설명")
    private Boolean status;
    @Schema(description = "메시지")
    private String message;
    @Schema(description ="결제수단 리스트 정보")
    List<PaymentInfoEntity> payList;
}
