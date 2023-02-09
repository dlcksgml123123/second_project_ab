package com.team5.household.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.entity.MemberInfoEntity;
import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.service.MemberService;
import com.team5.household.vo.LoginVO;
import com.team5.household.vo.MemberJoinVO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberInfoAPIController {
    private final MemberInfoRepository memberInfoRepository;
    private final MemberService mService;
    @PutMapping("/join")
    public Map<String, Object> memberJoin(@RequestBody MemberJoinVO data){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberJoinVO member = MemberJoinVO.builder()
        .MemberEmail(data.getMemberEmail())
        .MemberPwd(data.getMemberPwd())
        .build();
        return resultMap;
    }

    @PostMapping("/login")
    public Map<String, Object> postMemberLogin(@RequestBody LoginVO login) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        MemberInfoEntity entity = memberInfoRepository.findByMiIdAndMiPwd(
                login.getMemberEmail(), login.getMemberPwd());
        if (entity == null) {
            resultMap.put("status", false);
            resultMap.put("message", "잘못된 로그인 정보입니다.");
        } else {
            resultMap.put("status", true);
            resultMap.put("user", entity);
        }

        return resultMap;
    }
}
