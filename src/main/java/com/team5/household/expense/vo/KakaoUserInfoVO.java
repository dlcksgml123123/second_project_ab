package com.team5.household.expense.vo;

import com.team5.household.expense.Security.VO.TokenVO;
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
