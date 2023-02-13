package com.team5.household.lchwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.lchwork.entity.LchMemberInfoEntity;

public interface LchMemberRepository extends JpaRepository<LchMemberInfoEntity, Long>{
    
}
