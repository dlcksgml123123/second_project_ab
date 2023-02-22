package com.team5.household.entity;

import com.team5.household.vo.paymentVO.PaymentAddVO;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name="payment_info")
public class PaymentInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pi_seq") private Long piSeq;
    @Column(name="pi_type") private Integer piType;
    @Column(name="pi_name") private String piName;

    @Builder
    public PaymentInfoEntity(PaymentAddVO data){
        this.piType = data.getPaymentType();
        this.piName = data.getPaymentName();
    }
}
