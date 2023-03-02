package com.team5.household.lchwork.controller;

import java.util.List;

import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.lchwork.entity.LchExpenseHistoryEntity;
import com.team5.household.lchwork.repository.LchExpenseHistoryRepository;
import com.team5.household.lchwork.service.LchExpenseHistoryService;

import io.micrometer.common.lang.Nullable;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "월별 지출내역 정보관리", description = "월별 지출내역 API")
@RestController
@RequestMapping("api/expense/history")
public class LchExpenseHistoryController {
    @Autowired LchExpenseHistoryService ehService;
    @Autowired LchExpenseHistoryRepository ehRepo;

    @GetMapping("/monthly/list")
    @PageableAsQueryParam
    public ResponseEntity<Object> getMonthlyExpenseHistoryList(@RequestParam @Nullable String dt,
    @Parameter(hidden = true)
    @PageableDefault(size=20, sort="ehDate", direction = Sort.Direction.ASC) Pageable pageable
    ) {
        if(dt == null) dt = "";
        return new ResponseEntity<>(ehService.MonthExpenseHistoryList(dt, pageable), HttpStatus.OK);
    }
    @Operation(summary = "월별 지출내역 리스트", description = "회원의 월별 지출내역 리스트를 출력합니다.")
    @GetMapping(value = "/monthly/list2") 
    @PageableAsQueryParam
    public ResponseEntity<Object> getMonthlyExpenseHistoryList2(@RequestParam @Nullable Long member, String dt,
    @Parameter(description = "params로 데이터를 입력합니다.(member:회원번호, dt:날짜(년월))" ,hidden = true)
    @PageableDefault(size=20, sort="ehDate", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        if(dt == null) dt = "";
        return new ResponseEntity<>(ehService.MonthlyExpenseHistoryList(member, dt, pageable), HttpStatus.OK);
    }
}