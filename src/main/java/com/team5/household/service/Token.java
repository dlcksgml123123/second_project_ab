package com.team5.household.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.team5.household.entity.KakaoMemberInfoEntity;
import com.team5.household.entity.MemberInfoEntity;
import com.team5.household.repository.KakaoMemberRepository;
import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.security.provider.JwtTokenProvider;
import com.team5.household.security.vo.TokenVO;
import com.team5.household.vo.LoginVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class Token {
    private final MemberInfoRepository memberInfoRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final KakaoMemberRepository kakaoMemberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public TokenVO getToken(LoginVO login) {
        MemberInfoEntity entity = memberInfoRepository.findByEmailAndPwd(login.getEmail(), login.getPwd()).orElseThrow();

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(entity.getMiSeq(), entity.getPwd());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        
        return jwtTokenProvider.generateToken(authentication);
    }

    @Transactional
    public TokenVO getKakaoToken(String email) {
        KakaoMemberInfoEntity kakaoEntity = kakaoMemberRepository.findByEmail(email).orElseThrow();
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(kakaoEntity.getKmiSeq(), kakaoEntity.getEmail());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        
        return jwtTokenProvider.generateToken(authentication);
    }
}
