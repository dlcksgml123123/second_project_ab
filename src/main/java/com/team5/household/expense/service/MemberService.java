package com.team5.household.expense.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.team5.household.expense.entity.MemberInfoEntity;
import com.team5.household.expense.repository.MemberInfoRepository;
import com.team5.household.expense.vo.LoginVO;
import com.team5.household.expense.vo.MemberJoinVO;
import com.team5.household.expense.vo.MemberResponseVO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberInfoRepository infoRepository;
    
    @Transactional
    public MemberResponseVO joinMember(MemberJoinVO data) {
        MemberInfoEntity newMember = new MemberInfoEntity(data);
        infoRepository.save(newMember);
        MemberResponseVO response = new MemberResponseVO();
        response.setEmail(data.getEmail());
        response.setNickname(data.getNickname());
        return response;
    }
    
    public Map<String, Object> loginMember(LoginVO data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        MemberInfoEntity user = null;
        try {
            user = infoRepository.findByEmailAndPwd(data.getEmail(), data.getPwd());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (user == null) {
            resultMap.put("status", false);
            resultMap.put("message", "이메일 또는 비밀번호 오류입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        } else {
            resultMap.put("status", true);
            resultMap.put("message", "로그인 되었습니다.");
            resultMap.put("code", HttpStatus.ACCEPTED);
        }
        return resultMap;
    }
}
