package com.team5.household.lchwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.lchwork.entity.LchCultureDetailCategoryEntity;

public interface LchCultureDetailCategoryRepository extends JpaRepository<LchCultureDetailCategoryEntity, Long>{
    public LchCultureDetailCategoryEntity findByCdcName(String cdcName);
    public List<LchCultureDetailCategoryEntity> findByCdcCcSeq(Long cdcCcSeq);
    public LchCultureDetailCategoryEntity findBycdcSeq(Long seq);
    public Integer countByCdcName(String cdcName);
    public LchCultureDetailCategoryEntity findByCdcNameAndCdcCcSeq(String cdcName, Long cdcCcSeq);
    
    
}
