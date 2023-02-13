package com.team5.household.expense.api;

import ch.qos.logback.core.model.Model;
import com.team5.household.expense.entity.ExpenseEntity;
import com.team5.household.expense.repository.ExpenseRepository;
import com.team5.household.expense.service.ExpenseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.converters.models.PageableAsQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Tag(name = "지출내역 관리 ", description = "지출내역 CRUD API")
@RestController
@RequestMapping("/api/expense")
public class ExpenseAPIController {
    @Autowired ExpenseRepository eRepo;
    @Autowired ExpenseService eService;

    @Operation(summary = "지출내역 번호 내역검색", description = "지출내역번호로 검색을 합니다.")
    @GetMapping("/ehsearch")
    public Map<String, Object> getmsList(
            @Parameter(description = "지출내역 번호") @RequestParam Long ehSeq
    )
     {
         Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
         ExpenseEntity list = eRepo.findByEhSeq(ehSeq);
         resultMap.put("expense", list);
         return resultMap;
    }

    @Operation(summary = "회원번호 검색", description = "특정 회원의 최대 20개의 지출내역을 출력합니다.")
    @GetMapping("/page")
    @PageableAsQueryParam
    public ResponseEntity<Object> getListPage
            (@Parameter(description = "회원번호") @RequestParam Long ehMiSeq,
             @Parameter(hidden = true) Pageable pageable)
    {
        return new ResponseEntity<>(eService.getExpenseList(ehMiSeq, pageable), HttpStatus.OK);

    }

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
    @PostMapping(value = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> addExpense(
            @Parameter(description = "지출내역 제목", example ="") @RequestParam String ehTitle,
            @Parameter(description = "날짜", example = "2022-02-09T00:00:00") @RequestParam LocalDateTime ehDate,
            @Parameter(description = "회원 번호", example ="") @RequestParam Long ehMiSeq,
            @Parameter(description = "결제수단 번호", example ="") @RequestParam Long ehPiSeq,
            @Parameter(description = "금액", example ="") @RequestParam Long ehPrice,
            @Parameter(description = "가게 이름", example ="") @RequestParam String ehStoreName,
            @Parameter(description = "이미지 파일", example ="") @Nullable @RequestPart MultipartFile ehImgFile,
            @Parameter(description = "가게 주소", example ="") @RequestParam String ehLocation,
            @Parameter(description = "잔액", example ="") @RequestParam Long ehBalance,
            @Parameter(description = "문화 대분류 번호", example ="") @RequestParam Long ehCcSeq,
            @Parameter(description = "문화 소분류 번호", example ="") @RequestParam Long ehCdcSeq,
            @Parameter(description = "내용", example ="") @Nullable @RequestParam String ehContent
    ) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        eService.addEvent(ehTitle, ehDate, ehMiSeq, ehPiSeq, ehPrice, ehStoreName, ehImgFile, ehContent, ehLocation, ehBalance, ehCcSeq, ehCdcSeq);
        resultMap.put("status", true);
        resultMap.put("message", "내역이 등록되었습니다.");
        resultMap.put("code", HttpStatus.CREATED);
        return resultMap;
    }
}


