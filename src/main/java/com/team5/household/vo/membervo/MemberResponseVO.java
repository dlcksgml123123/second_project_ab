package com.team5.household.vo.membervo;

import lombok.Builder;
import lombok.Data;

@Data
public class MemberResponseVO {
    private String email;
    private String nickname;
    private String message;

}
