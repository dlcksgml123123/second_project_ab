package com.team5.household.expense.vo.memberVO;

import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
//회원정보를 수정할 때 사용
public class MemberUpdateVO {
    @Schema(description = "회원이름")
    private String name;
    @Schema(description = "변경될 닉네임")
    private String nickname;
    @Schema(description = "회원 생년월일(입력 예시:yyyy-mm-dd)")
    private LocalDate birth;
    @Schema(description = "회원 성별(1:남성/2:여성)")
    private Integer gen;
    @Schema(description = "회원 직업")
    private String job;
}
