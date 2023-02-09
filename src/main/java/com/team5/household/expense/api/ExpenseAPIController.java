package com.team5.household.expense.api;

import com.team5.household.expense.entity.ExpenseEntity;
import com.team5.household.expense.repository.ExpenseRepository;
import com.team5.household.expense.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "지출내역 관리 ", description = "지출내역 CRUD API")
@RestController
@RequestMapping("/api/expense")
public class ExpenseAPIController {
    @Autowired ExpenseRepository eRepo;
    @Autowired ExpenseService eService;


    @Operation(summary = "지출내역 리스트", description = "지출내역 리스트를 보여줍니다.")
    @GetMapping("/list") // 접근경로
    public Map<String, Object> getExpense()
    {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<ExpenseEntity> list = eRepo.findAll();
        resultMap.put("expense", list);
        return resultMap;
    }

    @Operation(summary = "지출내역 생성", description = "지출내역을 생성합니다.")
    @PostMapping("/add")
    public Map<String, Object> addExpense(@RequestParam String ehTitle, @RequestParam LocalDate ehDate,
    @RequestParam Long ehMiSeq, @RequestParam Long ehPiSeq, @RequestParam Long ehPrice, @RequestParam String ehStoreName,
    @RequestPart MultipartFile ehImgFile, @RequestParam String ehLocation, @RequestParam Long ehBalance,
    @RequestParam Long ehCcSeq, @RequestParam Long ehCdcSeq, @RequestParam String ehContent
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        eService.addEvent(ehTitle, ehDate, ehMiSeq, ehPiSeq, ehPrice, ehStoreName, ehImgFile, ehContent, ehLocation, ehBalance, ehCcSeq, ehCdcSeq);
        resultMap.put("status", true);
        resultMap.put("message", "내역이 등록되었습니다.");
        resultMap.put("code", HttpStatus.CREATED);
        return resultMap;
    }
}


