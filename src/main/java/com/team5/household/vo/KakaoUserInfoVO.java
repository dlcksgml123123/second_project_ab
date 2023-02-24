package com.team5.household.vo;

import com.team5.household.security.vo.TokenVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
public class KakaoUserInfoVO {
    private String email;
    private String nickname;
    private TokenVO tokenVO;

    @Builder
    public KakaoUserInfoVO(String email, String nickname){
        this.email = email;
        this.nickname = nickname;
    }
}
