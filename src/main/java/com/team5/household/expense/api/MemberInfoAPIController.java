package com.team5.household.expense.api;

import com.team5.household.expense.vo.memberVO.LoginVO;
import com.team5.household.expense.vo.memberVO.MemberJoinVO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.expense.service.MemberService;
import com.team5.household.expense.vo.memberVO.MemberResponseVO;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberInfoAPIController {
    private final MemberService mService;
    
    @PostMapping("/join")
    public ResponseEntity<MemberResponseVO> postMemberJoin(@RequestBody MemberJoinVO data){
        MemberResponseVO response = mService.joinMember(data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping("/login")
    public ResponseEntity<MemberResponseVO> postMemberLogin(@RequestBody LoginVO login) {
        MemberResponseVO memberData = mService.loginMember(login);
        return new ResponseEntity<>(memberData, HttpStatus.OK);
    }

}
