package com.team5.household.expense.vo.paymentVO;

import com.team5.household.expense.entity.MemberInfoEntity;
import com.team5.household.expense.entity.PaymentInfoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentSaveVO {
    private MemberInfoEntity member;
    private PaymentInfoEntity payment;
}
