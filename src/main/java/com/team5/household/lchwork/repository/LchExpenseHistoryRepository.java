package com.team5.household.lchwork.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team5.household.lchwork.entity.LchExpenseHistoryEntity;

public interface LchExpenseHistoryRepository extends JpaRepository<LchExpenseHistoryEntity, Long>{    
    // public Page<LchExpenseHistoryEntity> findByEhDateContains(String ehDate, Pageable pageable);
    @Query("select a from LchExpenseHistoryEntity a where YEAR(a.ehDate) = :year and MONTH(a.ehDate) = :month")
    Page<LchExpenseHistoryEntity> searchByEhDateContains(@Param("year") Integer year, @Param("month") Integer month, Pageable pageable);
}
