package com.team5.household.expense.api;

import java.util.LinkedHashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.expense.service.MemberService;
import com.team5.household.expense.vo.MemberJoinVO;
import com.team5.household.expense.vo.MemberResponseVO;
import com.team5.household.expense.vo.LoginVO;

import lombok.RequiredArgsConstructor;


@RestController
@Tag(name = "멤버 API", description = "멤버 회원가입 및 로그인 기능")
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberInfoAPIController {
    private final MemberService mService;

    @Operation(summary = "멤버 회원가입", description = "이메일과 비밀번호로 회원가입을 합니다.")
    @PostMapping("/join")
    public Map<String, Object> postMemberJoin(@RequestBody MemberJoinVO data){
        Map<String, Object> resultmap = new LinkedHashMap<>();
        MemberResponseVO response = mService.joinMember(data);
        resultmap.put("data", response);
        resultmap.put("status", HttpStatus.CREATED);
        return resultmap;
    }

    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인을 합니다.")
    @PostMapping("login")
    public Map<String, Object> postMemberLogin(@RequestBody LoginVO login) {
        Map<String, Object> resultMap = mService.loginMember(login);
        System.out.println(login);
        return resultMap;
    }
    
}
