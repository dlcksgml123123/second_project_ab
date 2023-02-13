package com.team5.household.expense.vo;

import com.team5.household.expense.entity.MemberInfoEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberJoinVO {
    @NotNull
    @Email
    @Schema(description = "이메일", example = "user1234@naver.com")
    private String email;


    @Pattern(regexp = "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{6,}$")
    @Schema(description = "비밀번호", example = "123456")
    private String pwd;

    @NotBlank
    @Schema(description = "닉네임", example = "닉네임")
    private String nickname;

    // public MemberJoinVO(MemberInfoEntity entity){
    //     this.MemberEmail = entity.getMiEmail();
    //     this.Membernickname = entity.getMiNickname();
    //     return new MemberJoinVO(entity);
    // }
}
