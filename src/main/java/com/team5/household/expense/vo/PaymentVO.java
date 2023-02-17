package com.team5.household.expense.vo;

import lombok.Data;

@Data
public class PaymentVO {
    private Long paymentSeq;
    private Integer paymentType;
    private String paymentName;
}
