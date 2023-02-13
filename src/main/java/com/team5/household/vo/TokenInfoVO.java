package com.team5.household.vo;

public class TokenInfoVO {
    // JWT에 대한 타입, Bearer 사용. 이후 HTTP에서 헤더에 prefix로 붙여주는 타입
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
