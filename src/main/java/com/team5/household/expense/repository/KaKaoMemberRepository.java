package com.team5.household.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.expense.entity.KakaoMemberInfoEntity;

public interface KakaoMemberRepository extends JpaRepository<KakaoMemberInfoEntity, Long>{
    public Integer countByEmail(String email);
    Optional<KakaoMemberInfoEntity> findByEmail(String email);
}
