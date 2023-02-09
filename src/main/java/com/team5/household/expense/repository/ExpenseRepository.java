package com.team5.household.expense.repository;

import com.team5.household.expense.entity.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseRepository extends JpaRepository<ExpenseEntity, Long> {


}
