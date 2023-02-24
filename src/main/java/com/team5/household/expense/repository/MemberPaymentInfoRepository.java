package com.team5.household.expense.repository;

import com.team5.household.expense.entity.MemberPaymentInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberPaymentInfoRepository extends JpaRepository<MemberPaymentInfoEntity, Long> {
    MemberPaymentInfoEntity findByMiSeq(Long seq);
}
