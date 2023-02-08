package com.team5.household.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.team5.household.entity.MemberEntity;

@Repository
public interface MemberInfoRepository extends JpaRepository<MemberEntity, Long> {
    
}
