package com.team5.household.expense.vo;


import com.team5.household.expense.entity.MemberInfoEntity;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
public class LoginVO {

    private String email;


    private String pwd;
}
