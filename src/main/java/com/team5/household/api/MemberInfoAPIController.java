package com.team5.household.api;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.entity.MemberInfoEntity;
import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.service.MemberService;
import com.team5.household.vo.LoginVO;


import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberInfoAPIController {
    private final MemberInfoRepository mRepo;
    private final MemberService mService;
    
    @PostMapping("login")
    public Map<String, Object> postMemberLogin(@RequestBody LoginVO login) {
        Map<String, Object> resultMap = mService.loginMember(login);
        System.out.println(login);
        return resultMap;
    }
    
}
