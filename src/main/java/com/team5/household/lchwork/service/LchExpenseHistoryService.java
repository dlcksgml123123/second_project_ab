package com.team5.household.lchwork.service;

import java.util.LinkedHashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.team5.household.lchwork.entity.ExpenseViewList;
import com.team5.household.lchwork.entity.LchExpenseHistoryEntity;
import com.team5.household.lchwork.repository.ExpenseViewListRepository;
import com.team5.household.lchwork.repository.LchExpenseHistoryRepository;

@Service
public class LchExpenseHistoryService {
    @Autowired LchExpenseHistoryRepository ehRepo;
    @Autowired ExpenseViewListRepository expenseViewRepo;
    // public Map<String, Object> MonthExpenseHistoryList(String month, Pageable pageable) {
    //     Page<LchExpenseHistoryEntity> page = ehRepo.findByEhDateContains(month, pageable);
    //     Map<String, Object> map = new LinkedHashMap<String, Object>();
    //     map.put("list", page.getContent());
    //     map.put("total", page.getTotalElements());
    //     map.put("totalPage", page.getTotalPages());
    //     map.put("currentPage", page.getNumber());
    //     return map;
    // }
    public Map<String, Object> MonthExpenseHistoryList(String dt, Pageable pageable) {
        String[] split = dt.split("-");
        Page<LchExpenseHistoryEntity> page = ehRepo.searchByEhDateContains(Integer.parseInt(split[0]), Integer.parseInt(split[1]), pageable);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("list", page.getContent());
        map.put("total", page.getTotalElements());
        map.put("totalPage", page.getTotalPages());
        map.put("currentPage", page.getNumber());
        return map;
    }
    public Map<String, Object> MonthlyExpenseHistoryList(Long member, String dt, Pageable pageable) {
        String[] split = dt.split("-");
        Page<ExpenseViewList> page = expenseViewRepo.getExpenseViewList(member, Integer.parseInt(split[0]), Integer.parseInt(split[1]), pageable);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("list", page.getContent());
        map.put("total", page.getTotalElements());
        map.put("totalPage", page.getTotalPages());
        map.put("currentPage", page.getNumber());
        return map;
    }

}
