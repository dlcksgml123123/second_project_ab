package com.team5.household.vo.membervo;

import com.team5.household.security.vo.TokenVO;

import lombok.Data;

@Data
public class MemberResponseVO {
    private String email;
    private String nickname;
    private String message;
    private TokenVO token;
    
    public void setMemberResponseVO(String email, String nickname){
        this.email = email;
        this.nickname = nickname;
    }
}