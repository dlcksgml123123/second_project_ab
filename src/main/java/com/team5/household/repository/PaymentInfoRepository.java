package com.team5.household.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.household.entity.PaymentEntity;

@Repository
public interface PaymentInfoRepository extends JpaRepository<PaymentEntity, Long> {
    
}