package com.team5.household.lchwork.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;

import com.team5.household.lchwork.VO.CategoryListVO;
import com.team5.household.lchwork.VO.CategoryTotalListVO;
import com.team5.household.lchwork.VO.responseVO.CategoryInputResponseVO;
import com.team5.household.lchwork.VO.responseVO.CategoryListResponseVO;
import com.team5.household.lchwork.VO.responseVO.CategoryUpdateResponseVO;
import com.team5.household.lchwork.VO.responseVO.DetailCategoryInputResponseVO;
import com.team5.household.lchwork.VO.responseVO.DetailCategoryListResponseVO;
import com.team5.household.lchwork.VO.responseVO.DetailCategoryUpdateResponseVO;
import com.team5.household.lchwork.entity.LchCultureCategoryEntity;
import com.team5.household.lchwork.entity.LchCultureDetailCategoryEntity;
import com.team5.household.lchwork.repository.LchCultureCategoryRepository;
import com.team5.household.lchwork.repository.LchCultureDetailCategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class LchCultureService {
    @Autowired LchCultureCategoryRepository ccRepo;
    @Autowired LchCultureDetailCategoryRepository cdcRepo;

    // 문화 대분류 카테고리 추가
    // public Map<String,Object> addCategory(LchCultureCategoryEntity data) {
    public CategoryInputResponseVO addCategory(LchCultureCategoryEntity data) {
        // Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        CategoryInputResponseVO response = new CategoryInputResponseVO();
        List<LchCultureCategoryEntity> ccList = ccRepo.findAll();
        LchCultureCategoryEntity ccEntity = ccRepo.findByCcName(data.getCcName());
        if(ccList.size() >= 20) {
            // resultMap.put("status", false);
            // resultMap.put("message", "대분류 카테고리를 더이상 추가할 수 없습니다.");
            response.setStatus(false);
            response.setMessage("대분류 카테고리를 더이상 추가할 수 없습니다.");
        }
        else {
            if(ccRepo.countByCcName(data.getCcName()) != 0) {

                // resultMap.put("status", false);
                // resultMap.put("message", data.getCcName()+"은/는 이미 등록된 대분류 카테고리 입니다.");
                response.setStatus(false);
                response.setMessage(data.getCcName()+"은/는 이미 등록된 대분류 카테고리 입니다.");
            }
            else {
                ccEntity = new LchCultureCategoryEntity(null, data.getCcName(), null);
                ccRepo.save(ccEntity);
                // resultMap.put("ccEntity", ccEntity);
                // resultMap.put("status", true);
                // resultMap.put("message", "대분류 카테고리 등록완료.");
                response.setCcEntity(ccEntity);
                response.setStatus(true);
                response.setMessage("대분류 카테고리 등록완료.");
            }
        }
        // return resultMap;
        return response;
    }

    // 문화 대분류 카테고리 수정
    public CategoryUpdateResponseVO updateCategory(Long no, String name) {
        CategoryUpdateResponseVO response = new CategoryUpdateResponseVO();
        Optional<LchCultureCategoryEntity> entityOpt = ccRepo.findById(no);
        if(entityOpt.isEmpty()) {
            response.setUpdated(false);
            response.setMessage("잘못된 카테고리 정보입니다.");
            // resultMap.put("updated", false);
            // resultMap.put("message", "잘못된 카테고리 정보입니다.");
        }
        else if(entityOpt.get().getCcName().equalsIgnoreCase(name)) {
            response.setUpdated(false);
            response.setMessage("이전과 같은 이름으로 수정할 수 없습니다.");
            // resultMap.put("updated", false);
            // resultMap.put("message", "이전과 같은 이름으로 수정할 수 없습니다.");
        }
        else if(ccRepo.countByCcName(name) != 0) {
            response.setUpdated(false);
            response.setMessage(name+"과/와 동일한 카테고리명이 이미 존재합니다.");
            // resultMap.put("updated", false);
            // resultMap.put("message", name+"과/와 동일한 카테고리명이 이미 존재합니다.");
        }
        else {
            LchCultureCategoryEntity ccEntity = new LchCultureCategoryEntity(no, name, null);
            ccRepo.save(ccEntity);
            response.setUpdated(true);
            response.setMessage("카테고리 정보 수정이 완료되었습니다.");
            response.setNo(no);
            response.setName(name);
            // resultMap.put("updated", true);
            // resultMap.put("no", no);
            // resultMap.put("name", name);
            // resultMap.put("message", "카테고리 정보 수정이 완료되었습니다.");
        }
        return response;
    }

    // 문화 대분류 카테고리 삭제
    @Transactional
    public void deleteCategory(Long no) {
        if(no != null)
        ccRepo.deleteById(no);
    }

    // 문화 소분류 카테고리 추가
    public DetailCategoryInputResponseVO addDetailCategory(LchCultureDetailCategoryEntity data) {
        DetailCategoryInputResponseVO response = new DetailCategoryInputResponseVO();
        List<LchCultureDetailCategoryEntity> cdcList = cdcRepo.findByCdcCcSeq(data.getCdcCcSeq());
        
        if(cdcList.size() >= 20) {
            response.setStatus(false);
            response.setMessage("해당 카테고리에 소분류 카테고리를 더이상 추가할 수 없습니다.(20개 제한)");
            // resultMap.put("status", false);
            // resultMap.put("message", "해당 카테고리에 소분류 카테고리를 더이상 추가할 수 없습니다.");
        }
        // else if(cdcRepo.countByCdcName(data.getCdcName()) != 0) {
        else if(cdcRepo.findByCdcNameAndCdcCcSeq(data.getCdcName(), data.getCdcCcSeq()) != null) {
            response.setStatus(false);
            response.setMessage(data.getCdcName()+"은/는 이미 등록된 소분류 카테고리 입니다.");
            // resultMap.put("status", false);
            // resultMap.put("message", data.getCdcName()+"은/는 이미 등록된 소분류 카테고리 입니다.");
        }
        else {
            LchCultureDetailCategoryEntity cdcEntity = new LchCultureDetailCategoryEntity(
                null, data.getCdcName(), data.getCdcCcSeq());
            cdcRepo.save(cdcEntity);
            response.setStatus(true);
            response.setMessage("소분류 카테고리 등록완료.");
            response.setCdcEntity(cdcEntity);
            // resultMap.put("cdcEntity", cdcEntity);
            // resultMap.put("status", true);
            // resultMap.put("message", "소분류 카테고리 등록완료.");
        }        
        return response;
    }
    
    // 문화 소분류 카테고리 수정
    public DetailCategoryUpdateResponseVO UpdateDetailCategory(Long no, String name) {
        DetailCategoryUpdateResponseVO response = new DetailCategoryUpdateResponseVO();
        Optional<LchCultureDetailCategoryEntity> entityOpt = cdcRepo.findById(no);
        if(entityOpt.isEmpty()) {
            response.setUpdated(false);
            response.setMessage("잘못된 카테고리 정보입니다.");
            // resultMap.put("updated", false);
            // resultMap.put("message", "잘못된 카테고리 정보입니다.");
        }
        else if(entityOpt.get().getCdcName().equalsIgnoreCase(name)) {
            response.setUpdated(false);
            response.setMessage("이전과 같은 이름으로 수정할 수 없습니다.");
            // resultMap.put("updated", false);
            // resultMap.put("message", "이전과 같은 이름으로 수정할 수 없습니다.");
        }
        else if(cdcRepo.findByCdcNameAndCdcCcSeq(name, entityOpt.get().getCdcCcSeq()) != null) {
            response.setUpdated(false);
            response.setMessage(name+"과/와 동일한 카테고리명이 이미 존재합니다.");
            // resultMap.put("updated", false);
            // resultMap.put("message", name+"과/와 동일한 카테고리명이 이미 존재합니다.");
        }
        else {
            LchCultureDetailCategoryEntity cdcEntity = new LchCultureDetailCategoryEntity(no, name, entityOpt.get().getCdcCcSeq());
            cdcRepo.save(cdcEntity);
            response.setUpdated(true);
            response.setMessage("카테고리 정보 수정이 완료되었습니다.");
            response.setNo(no);
            response.setName(name);
            // resultMap.put("updated", true);
            // resultMap.put("no", no);
            // resultMap.put("name", name);
            // resultMap.put("message", "카테고리 정보 수정이 완료되었습니다.");
        }
        return response;

    }
    
    // 문화 소분류 카테고리 삭제
    @Transactional
    public void deleteDetailCategory(Long no) {
        if(no != null) 
        cdcRepo.deleteById(no);
    }
    
    // 대분류 카테고리 리스트 출력
    public CategoryListResponseVO getCategoryList() {
        CategoryListResponseVO response = new CategoryListResponseVO();
        List<LchCultureCategoryEntity> cclist = ccRepo.findAll();

        if(cclist != null) {
            response.setStatus(true);
            response.setMessage("카테고리 리스트 조회 성공");
            response.setCclist(cclist);
            // map.put("status", true);
            // map.put("message", "카테고리 리스트 조회 성공");
            // map.put("cclist", cclist);
        }
        else {
            response.setStatus(false);
            response.setMessage("조회할 카테고리 리스트가 없습니다.");
            // map.put("status", false);
            // map.put("message", "조회할 카테고리 리스트가 없습니다.");
        }

        return response;
    }
    
    // 카테고리 전체 리스트 출력
    // public Map<String, Object> getCategoryTotalList(Long no) {
    //     Map<String, Object> resultMap = new LinkedHashMap<String,Object>();
    //     List<LchCultureDetailCategoryEntity> cdclist = cdcRepo.findByCdcCcSeq(no);
    //     List<CategoryTotalListVO> totallist = new ArrayList<>();
    //     for(CategoryTotalListVO list : no) {

    //     }

    //     return resultMap;
    // }

    // 소분류 카테고리 리스트 출력
    public DetailCategoryListResponseVO getDetailCategoryList(Long no) {
        DetailCategoryListResponseVO response = new DetailCategoryListResponseVO();
        List<LchCultureDetailCategoryEntity> cdclist = cdcRepo.findByCdcCcSeq(no);
        if(cdclist != null) {
            response.setStatus(true);
            response.setMessage("상세 카테고리 리스트 조회 성공");
            response.setCdclist(cdclist);
            // map.put("status", true);
            // map.put("message", "상세 카테고리 리스트 조회 성공");
            // map.put("cdclist", cdclist);
        }
        else{
            response.setStatus(false);
            response.setMessage("조회할 카테고리 리스트가 없습니다.");
            // map.put("status", false);
            // map.put("message", "조회할 카테고리 리스트가 없습니다.");
        }
        return response;
    }


}
