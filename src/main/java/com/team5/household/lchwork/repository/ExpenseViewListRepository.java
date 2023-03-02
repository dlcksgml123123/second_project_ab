package com.team5.household.lchwork.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.team5.household.lchwork.entity.ExpenseViewList;

public interface ExpenseViewListRepository extends JpaRepository<ExpenseViewList, Long>{
    @Query("select a from ExpenseViewList a where a.miSeq = :miSeq and YEAR(a.ehDate) = :year and MONTH(a.ehDate) = :month")
    Page<ExpenseViewList> getExpenseViewList(@Param("miSeq") Long miSeq, @Param("year") Integer year, @Param("month") Integer month, Pageable pageable);
}
