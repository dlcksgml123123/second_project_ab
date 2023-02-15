package com.team5.household.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.service.MemberService;
import com.team5.household.vo.LoginVO;
import com.team5.household.vo.MemberJoinVO;
import com.team5.household.vo.MemberResponseVO;

import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberInfoAPIController {
    private final MemberService mService;

    @PostMapping("/join")
    public Map<String, Object> postMemberJoin(@RequestBody MemberJoinVO data){
        Map<String, Object> resultmap = new LinkedHashMap<>();
        MemberResponseVO response = mService.joinMember(data);
        resultmap.put("data", response);
        resultmap.put("status", HttpStatus.CREATED);
        return resultmap;
    }
    @PostMapping("/login")
    public Map<String, Object> postMemberLogin(@RequestBody LoginVO login) {
        Map<String, Object> resultMap = mService.loginMember(login);
        return resultMap;
    }
    // @PostMapping("/update{seq}")
    // public ResponseEntity<Object> postMemberupdate(@PathVariable Long seq, @RequestBody UserUpdateVO userUpdate) {
    // Map<String, Object> map = new LinkedHashMap<>();
    // map = mService.updateMember();
    // return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    // }
}

