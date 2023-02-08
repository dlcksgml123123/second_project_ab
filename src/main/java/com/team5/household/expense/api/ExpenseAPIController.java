package com.team5.household.expense.api;

import com.team5.household.expense.entity.ExpenseEntity;
import com.team5.household.expense.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/expense")
public class ExpenseAPIController {
    @Autowired ExpenseRepository eRepo;
    @GetMapping ("/list") // 접근경로
    public Map<String, Object> getExpense(
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ExpenseEntity> list = eRepo.findAll();
        resultMap.put("expense", list);
        return resultMap;
    }

    @PostMapping("/add")
    public Map<String, Object> addExpense(@RequestParam String ehTitle, @RequestParam LocalDate ehDate,
                                          @RequestParam Long ehMiSeq, @RequestParam Long ehPiSeq, @RequestParam Long ehPrice, @RequestParam String ehStoreName,
                                          @RequestPart MultipartFile ehImgFile, @RequestParam String ehLocation, @RequestParam Long ehBalance,
                                          @RequestParam Long ehCcSeq, @RequestParam Long ehCdcSeq)
     {
         Map<String, Object> map = new LinkedHashMap<>();
    }
}


