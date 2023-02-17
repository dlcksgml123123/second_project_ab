package com.team5.household.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.team5.household.entity.PaymentInfoEntity;

public interface PaymentInfoRepository extends JpaRepository<PaymentInfoEntity, Long> {
    PaymentInfoEntity findByPiName(String piName);
    Optional<PaymentInfoEntity> findByPiSeq(Long seq);
    public Integer countByPiSeq(Long seq);
    // List<PaymentInfoEntity> paymentList(Integer type);
    // @Query(value = "select from PaymentInfoEntity where p.seq = :seq")
    List<PaymentInfoEntity> findByPiType(Integer piType);

}
