package com.team5.household.vo.membervo;

import java.util.List;

import com.team5.household.entity.MemberInfoEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberUpdateResponseVO {
    @Schema(description = "상태설명")
    private Boolean status;
    @Schema(description = "메세지")
    private String message;
    MemberUpdateVO data;
}