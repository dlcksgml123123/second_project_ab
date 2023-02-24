package com.team5.household.lchwork.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.lchwork.VO.responseVO.CategoryDeleteResponseVO;
import com.team5.household.lchwork.VO.responseVO.CategoryInputResponseVO;
import com.team5.household.lchwork.VO.responseVO.CategoryListResponseVO;
import com.team5.household.lchwork.VO.responseVO.CategoryUpdateResponseVO;
import com.team5.household.lchwork.VO.responseVO.DetailCategoryDeleteResponseVO;
import com.team5.household.lchwork.VO.responseVO.DetailCategoryInputResponseVO;
import com.team5.household.lchwork.VO.responseVO.DetailCategoryUpdateResponseVO;
import com.team5.household.lchwork.entity.LchCultureCategoryEntity;
import com.team5.household.lchwork.entity.LchCultureDetailCategoryEntity;
import com.team5.household.lchwork.service.LchCultureFixService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "카테고리 정보 관리(수정)", description = "카테고리정보 CRUD API")
@RestController
@RequestMapping("/api/fix/category")
public class CultureCategoryFixController {
    @Autowired LchCultureFixService cfService;
    // 문화 대분류 카테고리 추가
    @Operation(summary = "대분류 카테고리 추가", description = "대분류 카테고리를 최대 20개까지 추가합니다.")
    @PutMapping(value="/input")
    public ResponseEntity<CategoryInputResponseVO> categoryInput(
        @Parameter(description = "params로 데이터를 입력합니다.(ccName:대분류 카테고리 이름, ccMiSeq:회원번호)")
        // LchCultureCategoryEntity data
        @RequestParam String ccName, Long ccMiSeq
    ) {
        // LchCultureCategoryEntity entity = LchCultureCategoryEntity.builder().ccName(ccName).ccMiSeq(ccMiSeq).build();
        LchCultureCategoryEntity entity = new LchCultureCategoryEntity(null, ccName, ccMiSeq, null);
        CategoryInputResponseVO map = cfService.addCategoryfix(entity);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    // 문화 대분류 카테고리 수정
    @Operation(summary = "대분류 카테고리 수정", description = "선택한 대분류 카테고리 정보를 수정합니다.")
    @PostMapping(value="/update")
    public ResponseEntity<CategoryUpdateResponseVO> categoryUpdate(
        @Parameter(description = "params로 데이터를 입력합니다.(no:선택할 대분류 카테고리 번호, name:수정할 대분류 카테고리 이름, member:회원번호)")
        @RequestParam Long no, String name, Long member
    ) {
        CategoryUpdateResponseVO map = cfService.updateCategoryfix(no, name, member);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

    }

    // 문화 대분류 카테고리 삭제
    @Operation(summary = "대분류 카테고리 삭제", description = "선택한 대분류 카테고리 정보를 삭제합니다.")
    @GetMapping(value="/delete")
    public ResponseEntity<CategoryDeleteResponseVO> categoryDelete(
        @Parameter(description = "params로 데이터를 입력합니다.(no:삭제할 카테고리 번호)")
        @RequestParam Long no
    ) {
        CategoryDeleteResponseVO map = new CategoryDeleteResponseVO();
        cfService.deleteCategoryfix(no);
        map.setMessage("대분류 카테고리 정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    // 문화 소분류 카테고리 추가
    @Operation(summary = "소분류 카테고리 추가", description = "소분류 카테고리를 대분류 카테고리 하나당 최대 20개까지 추가합니다.")
    @PutMapping(value="/detail/input")
    public ResponseEntity<DetailCategoryInputResponseVO> categoryDetailInput(@RequestBody LchCultureDetailCategoryEntity data) {
        // LchCultureDetailCategoryEntity entity = new LchCultureDetailCategoryEntity(
        //     null, cdcName, cdcCcSeq);
        DetailCategoryInputResponseVO map = cfService.addDetailCategoryfix(data);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    // 문화 소분류 카테고리 수정
    @Operation(summary = "소분류 카테고리 수정", description = "선택한 소분류 카테고리 정보를 수정합니다.")
    @PostMapping(value="/detail/update")
    public ResponseEntity<DetailCategoryUpdateResponseVO> categoryDetailUpdate(
        @Parameter(description = "params로 데이터를 입력합니다.(no:선택할 소분류 카테고리 번호, name:수정할 소분류 카테고리 이름)")
        @RequestParam Long no, String name
    ) {
        DetailCategoryUpdateResponseVO map = cfService.UpdateDetailCategoryfix(no, name);
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
        cfService.deleteDetailCategoryfix(no);
        map.setMessage("소분류 카테고리 정보를 삭제했습니다.");
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);
    }

    // 문화 대분류 카테고리 리스트 출력
    @Operation(summary = "대분류 카테고리 리스트", description = "대분류 카테고리 리스트를 출력합니다.")
    @GetMapping(value="/list")
    public ResponseEntity<CategoryListResponseVO> getCategoryList(
        @Parameter(description = "params로 데이터를 입력합니다.(no:회원번호)")
        @RequestParam Long no
    ) {
        CategoryListResponseVO map = cfService.getCategoryListfix(no);
        return new ResponseEntity<>(map, HttpStatus.ACCEPTED);

    }

    
}
