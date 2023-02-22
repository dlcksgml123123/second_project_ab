package com.team5.household.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.expense.entity.PaymentInfoEntity;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfoEntity, Long> {
    PaymentInfoEntity findByPiName(String piName);
    PaymentInfoEntity findByPiType(Integer type);
//    Optional<PaymentInfoEntity> findByPiSeq(Long seq);
    PaymentInfoEntity findByPiSeq(Long piseq);
    public Integer countByPiSeq(Long piSeq);

}
