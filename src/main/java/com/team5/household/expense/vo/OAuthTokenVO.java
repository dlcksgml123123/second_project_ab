package com.team5.household.expense.vo;

import lombok.Data;

@Data
public class OAuthTokenVO {
    private String access_token;
    private String token_type;
    private String refresh_token;
    private Integer expires_in;
    private String scope;
    private Integer refresh_token_expires_in;
}
