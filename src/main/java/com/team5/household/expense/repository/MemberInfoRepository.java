package com.team5.household.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.expense.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity,Long> {
    public MemberInfoEntity findByEmailAndPwd(String email, String pwd);
    public Integer countByEmail(String email);
    Optional<MemberInfoEntity> findByMiSeq(Long miSeq);
    public Integer countByMiSeq(Long Seq);
}