package com.team5.household.vo;

import com.team5.household.security.vo.TokenVO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class KakaoUserInfoVO {
    private String email;
    private String nickname;
    private TokenVO authorization;
}
