package com.team5.household.lchwork.VO;

import java.util.List;

import com.team5.household.lchwork.entity.LchCultureCategoryEntity;
import com.team5.household.lchwork.entity.LchCultureDetailCategoryEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryTotalListVO {
    private Long ccSeq;
    private String ccName;
    private List<LchCultureDetailCategoryEntity> cdclist;
    
    public void listValue(LchCultureCategoryEntity data) {
        this.ccSeq = data.getCcSeq();
        this.ccName = data.getCcName();
    }

}
