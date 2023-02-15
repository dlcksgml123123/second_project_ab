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

import com.team5.household.lchwork.VO.CategoryDeleteResponseVO;
import com.team5.household.lchwork.VO.CategoryInputResponseVO;
import com.team5.household.lchwork.VO.CategoryListResponseVO;
import com.team5.household.lchwork.VO.CategoryUpdateResponseVO;
import com.team5.household.lchwork.VO.DetailCategoryDeleteResponseVO;
import com.team5.household.lchwork.VO.DetailCategoryInputResponseVO;
import com.team5.household.lchwork.VO.DetailCategoryListResponseVO;
import com.team5.household.lchwork.VO.DetailCategoryUpdateResponseVO;
import com.team5.household.lchwork.entity.LchCultureCategoryEntity;
import com.team5.household.lchwork.entity.LchCultureDetailCategoryEntity;
import com.team5.household.lchwork.service.LchCultureService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.Entity;

@Tag(name = "카테고리 정보 관리", description = "카테고리정보 CRUD API")
@RestController
@RequestMapping("/api/category")
public class CultureCategoryController {
    @Autowired LchCultureService cService;
    // 문화 대분류 카테고리 추가
    @Operation(summary = "대분류 카테고리 추가", description = "대분류 카테고리를 최대 20개까지 추가합니다.")
    @PutMapping(value="/input")
    public ResponseEntity<CategoryInputResponseVO> categoryInput2(
        @Parameter(description = "params로 데이터를 입력합니다.(ccName:대분류 카테고리 이름)")
        // LchCultureCategoryEntity data
        @RequestParam String ccName
    ) {
        LchCultureCategoryEntity entity = LchCultureCategoryEntity.builder().ccName(ccName).build();
        CategoryInputResponseVO map = cService.addCategory(entity);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    // 문화 대분류 카테고리 수정
    @Operation(summary = "대분류 카테고리 수정", description = "선택한 대분류 카테고리 정보를 수정합니다.")
    @PostMapping(value="/update")
    public ResponseEntity<CategoryUpdateResponseVO> categoryUpdate(
        @Parameter(description = "params로 데이터를 입력합니다.(no:선택할 대분류 카테고리 번호, name:수정할 대분류 카테고리 이름)")
        @RequestParam Long no, String name
    ) {
        CategoryUpdateResponseVO map = cService.updateCategory(no, name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

    }
    // 문화 대분류 카테고리 삭제
    @Operation(summary = "대분류 카테고리 삭제", description = "선택한 대분류 카테고리 정보를 삭제합니다.")
    @GetMapping(value="/delete")
    public ResponseEntity<CategoryDeleteResponseVO> categoryDelete(
        @Parameter(description = "params로 데이터를 입력합니다.(no:대분류 카테고리 번호)")
        @RequestParam Long no
    ) {
        CategoryDeleteResponseVO map = new CategoryDeleteResponseVO();
        cService.deleteCategory(no);
        map.setMessage("대분류 카테고리 정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    // 문화 소분류 카테고리 추가
    @Operation(summary = "소분류 카테고리 추가", description = "소분류 카테고리를 대분류 카테고리 하나당 최대 20개까지 추가합니다.")
    @PutMapping(value="/detail/input")
    public ResponseEntity<DetailCategoryInputResponseVO> categoryDetailInput(
        @Parameter(description = "requestbody로 데이터를 입력합니다.(cdcName:소분류 카테고리 이름, cdcCcSeq:대분류 카테고리 번호)")
        @RequestBody LchCultureDetailCategoryEntity data
        // @RequestParam String cdcName, Long cdcCcSeq
    ) {
        // LchCultureDetailCategoryEntity entity = new LchCultureDetailCategoryEntity(
        //     null, cdcName, cdcCcSeq);
        DetailCategoryInputResponseVO map = cService.addDetailCategory(data);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    // 문화 소분류 카테고리 수정
    @Operation(summary = "소분류 카테고리 수정", description = "선택한 소분류 카테고리 정보를 수정합니다.")
    @PostMapping(value="/detail/update")
    public ResponseEntity<DetailCategoryUpdateResponseVO> categoryDetailUpdate(
        @Parameter(description = "params로 데이터를 입력합니다.(no:선택할 소분류 카테고리 번호, name:수정할 소분류 카테고리 이름)")
        @RequestParam Long no, String name
    ) {
        DetailCategoryUpdateResponseVO map = cService.UpdateDetailCategory(no, name);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    // 문화 소분류 카테고리 삭제
    @Operation(summary = "소분류 카테고리 삭제", description = "선택한 소분류 카테고리 정보를 삭제합니다.")
    @GetMapping(value="/detail/delete")
    public ResponseEntity<DetailCategoryDeleteResponseVO> categoryDetailDelete(
        @Parameter(description = "params로 데이터를 입력합니다.(no:소분류 카테고리 번호)")
        @RequestParam Long no
    ) {
        DetailCategoryDeleteResponseVO map = new DetailCategoryDeleteResponseVO();
        cService.deleteDetailCategory(no);
        map.setMessage("소분류 카테고리 정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    // 문화 대분류 카테고리 리스트 출력
    @Operation(summary = "대분류 카테고리 리스트", description = "대분류 카테고리 리스트를 출력합니다.")
    @GetMapping(value="/list")
    public ResponseEntity<CategoryListResponseVO> getCategoryList(

    ) {
        CategoryListResponseVO map = cService.getCategoryList();
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

    }
    // 문화 소분류 카테고리 리스트 출력
    @Operation(summary = "소분류 카테고리 리스트", description = "선택한 대분류 카테고리에 해당하는 소분류 카테고리 리스트를 출력합니다.")
    @GetMapping(value="/detail/list")
    public ResponseEntity<DetailCategoryListResponseVO> getCategoryDetailList(
        @Parameter(description = "params로 데이터를 입력합니다.(no:선택할 대분류 카테고리 번호)")
        @RequestParam Long no
    ) {
        DetailCategoryListResponseVO map = cService.getDetailCategoryList(no);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }
    
}
