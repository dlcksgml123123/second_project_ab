package com.team5.household.expense.Security.VO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenVO {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
