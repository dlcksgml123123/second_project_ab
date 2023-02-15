package com.team5.household.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.household.entity.PaymentInfoEntity;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfoEntity, Long> {
    
}
