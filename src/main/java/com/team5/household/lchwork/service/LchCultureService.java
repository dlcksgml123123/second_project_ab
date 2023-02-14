package com.team5.household.lchwork.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestPart;

import com.team5.household.lchwork.VO.CategoryListVO;
import com.team5.household.lchwork.VO.CultureCategoryVO;
import com.team5.household.lchwork.VO.CultureDetailCategoryVO;
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
    public Map<String,Object> addCategory(LchCultureCategoryEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<LchCultureCategoryEntity> ccList = ccRepo.findAll();
        LchCultureCategoryEntity ccEntity = ccRepo.findByCcName(data.getCcName());
        if(ccList.size() >= 20) {
            resultMap.put("status", false);
            resultMap.put("message", "대분류 카테고리를 더이상 추가할 수 없습니다.");
        }
        else {
            if(ccRepo.countByCcName(data.getCcName()) != 0) {

                resultMap.put("status", false);
                resultMap.put("message", data.getCcName()+"은/는 이미 등록된 대분류 카테고리 입니다.");
            }
            else {
                ccEntity = new LchCultureCategoryEntity(null, data.getCcName());
                ccRepo.save(ccEntity);
                resultMap.put("ccEntity", ccEntity);
                resultMap.put("status", true);
                resultMap.put("message", "대분류 카테고리 등록완료.");
            }
        }
        return resultMap;
    }

    // 문화 대분류 카테고리 수정
    public Map<String, Object> updateCategory(Long no, String name) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Optional<LchCultureCategoryEntity> entityOpt = ccRepo.findById(no);
        if(entityOpt.isEmpty()) {
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "잘못된 카테고리 정보입니다.");
        }
        else if(entityOpt.get().getCcName().equalsIgnoreCase(name)) {
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "이전과 같은 이름으로 수정할 수 없습니다.");
        }
        else if(ccRepo.countByCcName(name) != 0) {
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "동일한 카테고리명이 이미 존재합니다.");
        }
        else {
            LchCultureCategoryEntity ccEntity = new LchCultureCategoryEntity(no, name);
            ccRepo.save(ccEntity);
            resultMap.put("updated", true);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "카테고리 정보 수정이 완료되었습니다.");
        }
        return resultMap;
    }

    // 문화 대분류 카테고리 삭제
    @Transactional
    public void deleteCategory(Long no) {
        if(no != null)
        ccRepo.deleteById(no);
    }

    // 문화 소분류 카테고리 추가
    public Map<String, Object> addDetailCategory(LchCultureDetailCategoryEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        List<LchCultureDetailCategoryEntity> cdcList = cdcRepo.findByCdcCcSeq(data.getCdcCcSeq());
        
        if(cdcList.size() >= 20) {
            resultMap.put("status", false);
            resultMap.put("message", "해당 카테고리에 소분류 카테고리를 더이상 추가할 수 없습니다.");
        }
        // else if(cdcRepo.countByCdcName(data.getCdcName()) != 0) {
        else if(cdcRepo.findByCdcNameAndCdcCcSeq(data.getCdcName(), data.getCdcCcSeq()) != null) {
            resultMap.put("status", false);
            resultMap.put("message", data.getCdcName()+"은/는 이미 등록된 소분류 카테고리 입니다.");
        }
        else {
            LchCultureDetailCategoryEntity cdcEntity = new LchCultureDetailCategoryEntity(
                null, data.getCdcName(), data.getCdcCcSeq());
            cdcRepo.save(cdcEntity);
            resultMap.put("cdcEntity", cdcEntity);
            resultMap.put("status", true);
            resultMap.put("message", "소분류 카테고리 등록완료.");
        }        
        return resultMap;
    }
    
    // 문화 소분류 카테고리 수정
    public Map<String, Object> UpdateDetailCategory(Long no, String name) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        Optional<LchCultureDetailCategoryEntity> entityOpt = cdcRepo.findById(no);
        if(entityOpt.isEmpty()) {
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "잘못된 카테고리 정보입니다.");
        }
        else if(entityOpt.get().getCdcName().equalsIgnoreCase(name)) {
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "이전과 같은 이름으로 수정할 수 없습니다.");
        }
        else if(cdcRepo.findByCdcNameAndCdcCcSeq(name, entityOpt.get().getCdcCcSeq()) != null) {
            resultMap.put("updated", false);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "동일한 카테고리명이 이미 존재합니다.");
        }
        else {
            LchCultureDetailCategoryEntity cdcEntity = new LchCultureDetailCategoryEntity(no, name, entityOpt.get().getCdcCcSeq());
            cdcRepo.save(cdcEntity);
            resultMap.put("updated", true);
            resultMap.put("no", no);
            resultMap.put("name", name);
            resultMap.put("message", "카테고리 정보 수정이 완료되었습니다.");
        }
        return resultMap;

    }
    
    // 문화 소분류 카테고리 삭제
    @Transactional
    public void deleteDetailCategory(Long no) {
        if(no != null) 
        cdcRepo.deleteById(no);
    }
    
    // 대분류 카테고리 리스트 출력
    public Map<String, Object> getCategoryList() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<LchCultureCategoryEntity> cclist = ccRepo.findAll();

        if(cclist != null) {
            map.put("status", true);
            map.put("message", "카테고리 리스트 조회 성공");
            map.put("cclist", cclist);
        }
        else {
            map.put("status", false);
            map.put("message", "조회할 카테고리 리스트가 없습니다.");
        }

        return map;
    }

    // 소분류 카테고리 리스트 출력
    public Map<String, Object> getDetailCategoryList(Long no) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        List<LchCultureDetailCategoryEntity> cdclist = cdcRepo.findByCdcCcSeq(no);
        if(cdclist != null) {
            map.put("status", true);
            map.put("message", "상세 카테고리 리스트 조회 성공");
            map.put("cdclist", cdclist);
        }
        else{
            map.put("status", false);
            map.put("message", "조회할 카테고리 리스트가 없습니다.");
        }
        return map;
    }

}
