package com.team5.household.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.expense.entity.MemberPaymentInfoEntity;

public interface MemberPaymentInfoRepository extends JpaRepository<MemberPaymentInfoEntity, Long> {
    com.team5.household.expense.entity.MemberPaymentInfoEntity findByMpiSeq(Long seq);
}
