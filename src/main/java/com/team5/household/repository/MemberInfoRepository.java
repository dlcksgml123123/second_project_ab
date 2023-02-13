package com.team5.household.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity,Long> {
    public MemberInfoEntity findByMiEmailAndMiPwd(String miEmail, String miPwd);
    public Integer countByMiEmail(String miEmail);
}