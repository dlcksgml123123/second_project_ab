package com.team5.household.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberJoinVO {
    private String MemberEmail;
    private String MemberPwd;
    private String Membernickname;
}
