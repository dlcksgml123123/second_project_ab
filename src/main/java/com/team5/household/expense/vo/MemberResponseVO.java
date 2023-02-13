package com.team5.household.expense.vo;

import lombok.Data;

@Data
public class MemberResponseVO {
    private String email;
    private String nickname;

    // public MemberResponseVO(MemberInfoEntity entity){
    //     this.MemberEmail = entity.getMiEmail();
    //     this.Membernickname = entity.getMiNickname();
    //     return R
    // }
}
