package com.team5.household.vo;

import com.team5.household.entity.MemberInfoEntity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginVO {
    private Long memberSeq;
    private String memberEmail;
    private String memberPwd;
}
