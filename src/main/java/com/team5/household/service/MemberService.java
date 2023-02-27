package com.team5.household.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.team5.household.entity.MemberInfoEntity;
import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.security.filter.JwtAuthenticationFilter;
import com.team5.household.security.provider.JwtTokenProvider;
import com.team5.household.security.vo.TokenVO;
import com.team5.household.vo.LoginVO;
import com.team5.household.vo.MemberJoinVO;
import com.team5.household.vo.membervo.MemberResponseVO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberInfoRepository infoRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    
    @Transactional
    public MemberResponseVO joinMember(MemberJoinVO data) {
        MemberInfoEntity newMember = new MemberInfoEntity(data);
        infoRepository.save(newMember);
        MemberResponseVO response = new MemberResponseVO();
        response.setEmail(data.getEmail());
        response.setNickname(data.getNickname());
        response.setMessage("회원가입이 완료되었습니다.");
        return response;
        
    }

    public MemberResponseVO loginMember(LoginVO data) {
        MemberInfoEntity member = infoRepository.findByEmailAndPwd(data.getEmail(), data.getPwd()).orElseThrow();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPwd(), member.getAuthorities());
        System.out.println(authenticationToken);
        // 여기서 문제가 발생합니다. 욕하고 싶다.
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        
        // SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(authentication);
        TokenVO tokenVO = jwtTokenProvider.generateToken(authentication);
        
        MemberResponseVO memberData = new MemberResponseVO();
        memberData.setEmail(member.getEmail());
        memberData.setNickname(member.getNickname());
        memberData.setMessage("로그인 성공");
        memberData.setToken(tokenVO);
        return memberData;
    }
}
