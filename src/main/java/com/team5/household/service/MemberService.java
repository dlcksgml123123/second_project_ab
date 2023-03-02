package com.team5.household.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.team5.household.entity.MemberInfoEntity;
import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.security.provider.JwtTokenProvider;
import com.team5.household.security.vo.TokenVO;
import com.team5.household.vo.LoginVO;
import com.team5.household.vo.MemberJoinVO;
import com.team5.household.vo.membervo.MemberResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberInfoRepository infoRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    
    public Boolean isDuplicatedEmail(String email){
        if(infoRepository.countByEmail(email) > 0) {
            return true;
        }
        return false;
    }

    public MemberResponseVO joinMember(MemberJoinVO data) throws Exception {
        if(isDuplicatedEmail(data.getEmail())){
            throw new Exception("중복된 이메일");
        }
        MemberInfoEntity newMember = new MemberInfoEntity(data);
        infoRepository.save(newMember);
        MemberInfoEntity member = infoRepository.findByEmailAndPwd(data.getEmail(), data.getPwd()).orElseThrow();
        MemberResponseVO response = new MemberResponseVO();
        response.setEmail(member.getEmail());
        response.setNickname(member.getNickname());
        response.setMessage("회원가입이 완료되었습니다.");
        return response;
        
    }

    public MemberResponseVO loginMember(LoginVO data) {
        MemberInfoEntity member = infoRepository.findByEmailAndPwd(data.getEmail(), data.getPwd()).orElseThrow();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPwd(), member.getAuthorities());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenVO tokenVO = jwtTokenProvider.generateToken(authentication);
        
        MemberResponseVO memberData = new MemberResponseVO();
        memberData.setEmail(member.getEmail());
        memberData.setNickname(member.getNickname());
        memberData.setRole(member.getMiRole());
        memberData.setMessage("로그인 성공");
        memberData.setToken(tokenVO);
        return memberData;
    }
}
