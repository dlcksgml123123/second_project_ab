package com.team5.household.vo;

import lombok.Data;

@Data
public class MemberResponseVO {
    private String MemberEmail;
    private String Membernickname;

    // public MemberResponseVO(MemberInfoEntity entity){
    //     this.MemberEmail = entity.getMiEmail();
    //     this.Membernickname = entity.getMiNickname();
    //     return R
    // }
}
