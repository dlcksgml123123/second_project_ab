package com.team5.household.lchwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.team5.household.lchwork.entity.LchCultureCategoryEntity;

public interface LchCultureCategoryRepository extends JpaRepository<LchCultureCategoryEntity, Long> {
    public LchCultureCategoryEntity findByCcName(String ccName);
    public LchCultureCategoryEntity findByCcSeq(Long ccSeq);
    public Integer countByCcName(String ccName);
    public Integer countByCcSeq(Long ccSeq);
    public List<LchCultureCategoryEntity> findByccSeq(Long ccSeq);
}
