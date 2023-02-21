package com.team5.household.vo.membervo;

import java.util.Date;
import java.util.List;

import com.team5.household.entity.MemberInfoEntity;

import lombok.Builder;
import lombok.Data;

@Data
public class UserUpdateVO {
    private String name;
    private Date birth;
    private Integer gen;
    private String job;
    private Boolean Status;
    private String message;
    List<MemberInfoEntity> memberList;
    
    public UserUpdateVO() {
    }
}
