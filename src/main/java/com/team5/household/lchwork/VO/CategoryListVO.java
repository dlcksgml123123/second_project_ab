package com.team5.household.lchwork.VO;

import java.util.List;

import com.team5.household.lchwork.entity.LchCultureCategoryEntity;

import lombok.Data;

@Data
public class CategoryListVO {
    private List<LchCultureCategoryEntity> list;
}
