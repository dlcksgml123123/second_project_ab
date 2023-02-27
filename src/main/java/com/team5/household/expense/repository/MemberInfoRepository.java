<<<<<<< HEAD:src/main/java/com/team5/household/repository/MemberInfoRepository.java
package com.team5.household.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity,Long> {
    public MemberInfoEntity findByEmailAndPwd(String email, String pwd);
    public Integer countByEmail(String email);
    Optional<MemberInfoEntity> findByEmail(String email);
    Optional<MemberInfoEntity> findByMiSeq(Long miSeq);
    public Integer countByMiSeq(Long Seq);
=======
package com.team5.household.expense.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.expense.entity.MemberInfoEntity;

public interface MemberInfoRepository extends JpaRepository<MemberInfoEntity,Long> {
    public MemberInfoEntity findByEmailAndPwd(String email, String pwd);
    public Integer countByEmail(String email);
    Optional<MemberInfoEntity> findByMiSeq(Long miSeq);
    public Integer countByMiSeq(Long Seq);
>>>>>>> c28ebf01235af6604410ef4af3d8bb8fce68ae77:src/main/java/com/team5/household/expense/repository/MemberInfoRepository.java
}