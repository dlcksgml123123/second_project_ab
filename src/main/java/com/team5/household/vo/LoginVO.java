package com.team5.household.vo;


import com.team5.household.entity.MemberInfoEntity;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginVO {
    private String memberEmail;
    private String memberPwd;
}
