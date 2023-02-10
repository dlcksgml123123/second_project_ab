package com.team5.household.expense.repository;

import com.team5.household.expense.entity.ExpenseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {
    ExpenseEntity findByEhSeq (Long ehSeq);
    public Page<ExpenseEntity> findPageByehMiSeq(Long ehMiSeq, Pageable pageable);
}
