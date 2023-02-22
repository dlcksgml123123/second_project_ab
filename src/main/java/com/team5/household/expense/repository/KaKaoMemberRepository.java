package com.team5.household.expense.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.expense.entity.KaKaoMemberInfoEntity;

public interface KaKaoMemberRepository extends JpaRepository<KaKaoMemberInfoEntity, Long> {
    public Integer countByEmail(String email);
}
