package com.team5.household.lchwork.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team5.household.lchwork.VO.responseVO.CategoryInputResponseVO;
import com.team5.household.lchwork.VO.responseVO.CategoryListResponseVO;
import com.team5.household.lchwork.VO.responseVO.CategoryUpdateResponseVO;
import com.team5.household.lchwork.VO.responseVO.DetailCategoryInputResponseVO;
import com.team5.household.lchwork.VO.responseVO.DetailCategoryUpdateResponseVO;
import com.team5.household.lchwork.entity.LchCultureCategoryEntity;
import com.team5.household.lchwork.entity.LchCultureDetailCategoryEntity;
import com.team5.household.lchwork.repository.LchCultureCategoryRepository;
import com.team5.household.lchwork.repository.LchCultureDetailCategoryRepository;

import jakarta.transaction.Transactional;

@Service
public class LchCultureFixService {
    @Autowired LchCultureCategoryRepository ccRepo;
    @Autowired LchCultureDetailCategoryRepository cdcRepo;

    // 문화 대분류 카테고리 추가(수정)
    public CategoryInputResponseVO addCategoryfix(LchCultureCategoryEntity data) {
        CategoryInputResponseVO response = new CategoryInputResponseVO();
        List<LchCultureCategoryEntity> ccList = ccRepo.findByCcMiSeq(data.getCcMiSeq());
        LchCultureCategoryEntity ccEntity = ccRepo.findByCcNameAndCcMiSeq(data.getCcName(), data.getCcMiSeq());
        if(ccList.size() >= 20) {
            response.setStatus(false);
            response.setMessage("대분류 카테고리를 더이상 추가할 수 없습니다.");
        }
        else {
            if(ccRepo.findByCcNameAndCcMiSeq(data.getCcName(), data.getCcMiSeq()) != null) {
                response.setStatus(false);
                response.setMessage(data.getCcName()+"은/는 이미 등록된 대분류 카테고리 입니다.");
            }
            else {
                ccEntity = new LchCultureCategoryEntity(null, data.getCcName(), data.getCcMiSeq(), null);
                ccRepo.save(ccEntity);
                response.setCcEntity(ccEntity);
                response.setStatus(true);
                response.setMessage("대분류 카테고리 등록완료.");                             
            }
        }
        return response;
    }
    // 문화 대분류 카테고리 수정(수정)
    public CategoryUpdateResponseVO updateCategoryfix(Long no, String name, Long member) {
        CategoryUpdateResponseVO response = new CategoryUpdateResponseVO();
        Optional<LchCultureCategoryEntity> entityOpt = ccRepo.findById(no);
        if(entityOpt.isEmpty()) {
            response.setUpdated(false);
            response.setMessage("잘못된 카테고리 정보입니다.");
        }
        else if(entityOpt.get().getCcMiSeq() != member) {
            response.setUpdated(false);
            response.setMessage("잘못된 회원정보입니다.");
        }
        else if(entityOpt.get().getCcName().equalsIgnoreCase(name)) {
            response.setUpdated(false);
            response.setMessage("이전과 같은 이름으로 수정할 수 없습니다.");
        }
        else if(ccRepo.countByCcNameAndCcMiSeq(name, member) != 0) {
            response.setUpdated(false);
            response.setMessage(name+"과/와 동일한 카테고리명이 이미 존재합니다.");
        }
        else {
            LchCultureCategoryEntity ccEntity = ccRepo.findByCcSeq(no); 
            ccEntity.setCcName(name); // 수정할때 no까지 새로 입력하게되면 상세카테고리에 null뜸
            ccRepo.save(ccEntity);
            response.setUpdated(true);
            response.setMessage("카테고리 정보 수정이 완료되었습니다.");
            response.setNo(no);
            response.setName(name);
        }
        return response;
    }
    // 문화 대분류 카테고리 삭제(수정)
    @Transactional
    public void deleteCategoryfix(Long no) {
        if(no != null)
        ccRepo.deleteById(no);
    }
    // 문화 소분류 카테고리 추가(수정)
    public DetailCategoryInputResponseVO addDetailCategoryfix(LchCultureDetailCategoryEntity data) {
        DetailCategoryInputResponseVO response = new DetailCategoryInputResponseVO();
        List<LchCultureDetailCategoryEntity> cdcList = cdcRepo.findByCdcCcSeq(data.getCdcCcSeq());
        // System.out.println("============================"+data);
        if(cdcList.size() >= 20) {
            response.setStatus(false);
            response.setMessage("해당 카테고리에 소분류 카테고리를 더이상 추가할 수 없습니다.(20개 제한)");
        }
        else if(cdcRepo.findByCdcNameAndCdcCcSeq(data.getCdcName(), data.getCdcCcSeq()) != null) {
            response.setStatus(false);
            response.setMessage(data.getCdcName()+"은/는 이미 등록된 소분류 카테고리 입니다.");
        }
        else {
            LchCultureDetailCategoryEntity cdcEntity = new LchCultureDetailCategoryEntity(
                null, data.getCdcName(), data.getCdcCcSeq());
            cdcRepo.save(cdcEntity);
            response.setStatus(true);
            response.setMessage("소분류 카테고리 등록완료.");
            response.setCdcEntity(cdcEntity);
        }
        
        return response;
    }
    
    // 문화 소분류 카테고리 수정(수정)
    public DetailCategoryUpdateResponseVO UpdateDetailCategoryfix(Long no, String name) {
        DetailCategoryUpdateResponseVO response = new DetailCategoryUpdateResponseVO();
        Optional<LchCultureDetailCategoryEntity> entityOpt = cdcRepo.findById(no);
        if(entityOpt.isEmpty()) {
            response.setUpdated(false);
            response.setMessage("잘못된 카테고리 정보입니다.");
        }
        else if(entityOpt.get().getCdcName().equalsIgnoreCase(name)) {
            response.setUpdated(false);
            response.setMessage("이전과 같은 이름으로 수정할 수 없습니다.");
        }
        else if(cdcRepo.findByCdcNameAndCdcCcSeq(name, entityOpt.get().getCdcCcSeq()) != null) {
            response.setUpdated(false);
            response.setMessage(name+"과/와 동일한 카테고리명이 이미 존재합니다.");
        }
        else {
            LchCultureDetailCategoryEntity cdcEntity = new LchCultureDetailCategoryEntity(no, name, entityOpt.get().getCdcCcSeq());
            cdcRepo.save(cdcEntity);
            response.setUpdated(true);
            response.setMessage("카테고리 정보 수정이 완료되었습니다.");
            response.setNo(no);
            response.setName(name);
        }
        return response;

    }
    
    // 문화 소분류 카테고리 삭제(수정)
    @Transactional
    public void deleteDetailCategoryfix(Long no) {
        if(no != null) 
        cdcRepo.deleteById(no);
    }
    
    // 대분류 카테고리 리스트 출력(수정)
    public CategoryListResponseVO getCategoryListfix(Long no) {
        CategoryListResponseVO response = new CategoryListResponseVO();
        List<LchCultureCategoryEntity> cclist = ccRepo.findByCcMiSeq(no);
        if(cclist != null) {
            response.setStatus(true);
            response.setMessage("카테고리 리스트 조회 성공");
            response.setCclist(cclist);
        }
        else {
            response.setStatus(false);
            response.setMessage("조회할 카테고리 리스트가 없습니다.");
        }

        return response;
    }
}
