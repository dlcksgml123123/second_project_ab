package com.team5.household.repository;

import java.lang.StackWalker.Option;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.entity.KakaoMemberInfoEntity;

public interface KakaoMemberRepository extends JpaRepository<KakaoMemberInfoEntity, Long>{
    public Integer countByEmail(String email);
    Optional<KakaoMemberInfoEntity> findByEmail(String email);
}
