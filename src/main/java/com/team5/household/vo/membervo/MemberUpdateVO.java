package com.team5.household.vo.membervo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class MemberUpdateVO {
    private String name;
    private String nickname;
    private LocalDate birth;
    private Integer gen;
    private String job;
}
