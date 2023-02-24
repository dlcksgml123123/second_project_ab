package com.team5.household.vo;


import com.team5.household.security.vo.TokenVO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginVO {
    private String email;
    private String pwd;
    // private TokenVO tokenVO;
}
