package com.team5.household.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.household.expense.entity.PaymentInfoEntity;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentInfoEntity, Long> {
    
}
