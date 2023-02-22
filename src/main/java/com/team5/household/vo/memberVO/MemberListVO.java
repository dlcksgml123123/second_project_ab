package com.team5.household.vo.memberVO;

import java.util.List;

import com.team5.household.entity.MemberInfoEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
//회원전체 리스트 조회할 때 사용
public class MemberListVO {
    @Schema(description = "상태설명")
    private Boolean status;
    @Schema(description = "메시지")
    private String message;
    @Schema(description = "회원정보 리스트 정보")
    List<MemberInfoEntity> MemberList;
}
