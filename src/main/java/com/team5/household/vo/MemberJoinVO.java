package com.team5.household.vo;

import com.team5.household.entity.MemberInfoEntity;

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
    private String email;
    
    @Pattern(regexp = "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{6,}$")
    private String pwd;

    @NotBlank
    private String nickname;

    // public MemberJoinVO(MemberInfoEntity entity){
    //     this.MemberEmail = entity.getMiEmail();
    //     this.Membernickname = entity.getMiNickname();
    //     return new MemberJoinVO(entity);
    // }
}
