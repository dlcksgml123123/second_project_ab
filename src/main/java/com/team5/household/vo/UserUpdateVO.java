package com.team5.household.vo;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateVO {
    private String name;
    private Date birth;
    private Integer gen;
    private String job;
}
