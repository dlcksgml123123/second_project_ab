package com.team5.household.expense.vo.memberVO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

    @NotNull
    @ColumnDefault("USER")
    private String role;

}
