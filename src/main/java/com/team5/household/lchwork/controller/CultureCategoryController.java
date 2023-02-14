package com.team5.household.lchwork.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.lchwork.VO.CultureCategoryVO;
import com.team5.household.lchwork.entity.LchCultureCategoryEntity;
import com.team5.household.lchwork.entity.LchCultureDetailCategoryEntity;
import com.team5.household.lchwork.service.LchCultureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "카테고리 정보 관리", description = "카테고리정보 CRUD API")
@RestController
@RequestMapping("/api/category")
public class CultureCategoryController {
    @Autowired LchCultureService cService;
    // @PutMapping("input")
    // public ResponseEntity<Object> categoryInput(String name) {
    //     Map<String,Object> map = cService.addCategory(name);
    //     return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    // }

    // @PutMapping("/input3")
    // public ResponseEntity<Object> categoryDetailInput(CultureCategoryVO data) {
    //     Map<String,Object> map = cService.addDetailCategory(data);
    //     return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    // }

    // 문화 대분류 카테고리 추가
    @Operation(summary = "대분류 카테고리 추가", description = "대분류 카테고리를 최대 20개까지 추가합니다.")
    @PutMapping(value="/input")
    public ResponseEntity<Object> categoryInput2(
        @Parameter(description = "params로 데이터를 입력합니다.(ccName:대분류 카테고리 이름)")
        // LchCultureCategoryEntity data
        @RequestParam String ccName
    ) {
        LchCultureCategoryEntity entity = LchCultureCategoryEntity.builder().ccName(ccName).build();
        Map<String,Object> map = cService.addCategory(entity);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    // 문화 대분류 카테고리 삭제
    @Operation(summary = "대분류 카테고리 삭제", description = "선택한 대분류 카테고리 정보를 삭제합니다.")
    @GetMapping(value="/delete")
    public ResponseEntity<Object> categoryDelete(
        @Parameter(description = "params로 데이터를 입력합니다.(no:대분류 카테고리 번호)")
        @RequestParam Long no
    ) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        cService.deleteCategory(no);
        map.put("message", "대분류 카테고리 정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    // 문화 대분류 카테고리 수정
    @Operation(summary = "대분류 카테고리 수정", description = "선택한 대분류 카테고리 정보를 수정합니다.")
    @PostMapping(value="/update")
    public ResponseEntity<Object> categoryUpdate(
        @Parameter(description = "params로 데이터를 입력합니다.(no:선택할 대분류 카테고리 번호, name:수정할 대분류 카테고리 이름)")
        @RequestParam Long no, String name
    ) {
        Map<String, Object> map = cService.updateCategory(no, name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

    }
    // 문화 소분류 카테고리 추가
    @Operation(summary = "소분류 카테고리 추가", description = "소분류 카테고리를 대분류 카테고리 하나당 최대 20개까지 추가합니다.")
    @PutMapping(value="/detail/input")
    public ResponseEntity<Object> categoryDetailInput(
        @Parameter(description = "requestbody로 데이터를 입력합니다.(cdcName:소분류 카테고리 이름, cdcCcSeq:대분류 카테고리 번호)")
        @RequestBody LchCultureDetailCategoryEntity data
        // @RequestParam String cdcName, Long cdcCcSeq
    ) {
        // LchCultureDetailCategoryEntity entity = new LchCultureDetailCategoryEntity(
        //     null, cdcName, cdcCcSeq);
        Map<String, Object> map = cService.addDetailCategory(/*entity*/data);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    // 문화 소분류 카테고리 삭제
    @Operation(summary = "소분류 카테고리 삭제", description = "선택한 소분류 카테고리 정보를 삭제합니다.")
    @GetMapping(value="/detail/delete")
    public ResponseEntity<Object> categoryDetailDelete(
        @Parameter(description = "params로 데이터를 입력합니다.(no:소분류 카테고리 번호)")
        @RequestParam Long no
    ) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        cService.deleteDetailCategory(no);
        map.put("message", "소분류 카테고리 정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    // 문화 소분류 카테고리 수정
    @Operation(summary = "소분류 카테고리 수정", description = "선택한 소분류 카테고리 정보를 수정합니다.")
    @PostMapping(value="/detail/update")
    public ResponseEntity<Object> categoryDetailUpdate(
        @Parameter(description = "params로 데이터를 입력합니다.(no:선택할 소분류 카테고리 번호, name:수정할 소분류 카테고리 이름)")
        @RequestParam Long no, String name
    ) {
        Map<String, Object> map = cService.UpdateDetailCategory(no, name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    // 문화 대분류 카테고리 리스트 출력
    @Operation(summary = "대분류 카테고리 리스트", description = "대분류 카테고리 리스트를 출력합니다.")
    @GetMapping(value="/list")
    public ResponseEntity<Object> getCategoryList(

    ) {
        Map<String, Object> map = cService.getCategoryList();
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

    }
    // 문화 소분류 카테고리 리스트 출력
    @Operation(summary = "소분류 카테고리 리스트", description = "선택한 대분류 카테고리에 해당하는 소분류 카테고리 리스트를 출력합니다.")
    @GetMapping(value="/detail/list")
    public ResponseEntity<Object> getCategoryDetailList(
        @Parameter(description = "params로 데이터를 입력합니다.(no:선택할 대분류 카테고리 번호)")
        @RequestParam Long no
    ) {
        Map<String, Object> map = cService.getDetailCategoryList(no);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    
}
