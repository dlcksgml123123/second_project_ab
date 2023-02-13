package com.team5.household.expense.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="payment_info")
public class PaymentInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pi_seq") private Long piSeq;
    @Column(name="pi_type") private String piType;
    @Column(name="pi_name") private String piName;
}
