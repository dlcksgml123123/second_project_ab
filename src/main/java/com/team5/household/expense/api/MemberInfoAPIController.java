package com.team5.household.expense.api;

import com.team5.household.expense.vo.memberVO.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.team5.household.expense.service.MemberService;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberInfoAPIController {
    private final MemberService mService;

    @PostMapping("/join")
    public ResponseEntity<MemberResponseVO> postMemberJoin(@RequestBody MemberJoinVO data) throws Exception {
        MemberResponseVO response = mService.joinMember(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<MemberResponseVO> postMemberLogin(@RequestBody LoginVO login) {
        MemberResponseVO memberData = mService.loginMember(login);
        return new ResponseEntity<>(memberData, HttpStatus.OK);
    }

    //회원정보 전체조회
    @Operation(summary = "회원 리스트 전체조회", description = "회원정보를 모두 조회합니다.")
    @GetMapping("/listall")
    public ResponseEntity<MemberListVO> getMemberListAll() {
        MemberListVO memberList = mService.listAll();
        return new ResponseEntity<>(memberList, HttpStatus.CREATED);
    }

    //회원정보 수정
    @Operation(summary = "회원정보 수정", description = "회원정보에 입력되지 않은 내용을 입력합니다.")
    @PostMapping("/update/{seq}")
    public ResponseEntity<MemberUpdateResponseVO> postMemberupdate(@PathVariable Long seq, @RequestBody MemberUpdateVO data) {
        MemberUpdateResponseVO response = mService.updateMember(seq, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //회원정보 삭제
    @Operation(summary = "회원정보 삭제", description = "url에 seq번호를 이용해서 회원의 내용을 삭제합니다.")
    @DeleteMapping("/delete/{seq}")
    public ResponseEntity<UserResponseVO> deleteMember(
            @Parameter(description = "seq번호를 입력해서 삭제를 합니다.")
            @PathVariable Long seq) {
        UserResponseVO response = mService.deleteMember(seq);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
