package com.team5.household.vo;


import com.team5.household.entity.PaymentInfoEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentAddVO {
    private int paymentType;
    private String paymentName;

    public PaymentAddVO(PaymentInfoEntity entity) {
        this.paymentName = entity.getPiName();
        this.paymentType = entity.getPiType();
    }
}
