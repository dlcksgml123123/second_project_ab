package com.team5.household.vo;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginVO {
    private String email;
    private String pwd;
}
