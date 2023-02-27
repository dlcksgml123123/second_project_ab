package com.team5.household.expense.service;

import com.team5.household.expense.entity.KaKaoMemberInfoEntity;
import com.team5.household.expense.entity.MemberInfoEntity;
import com.team5.household.expense.repository.KaKaoMemberRepository;
import com.team5.household.expense.repository.MemberInfoRepository;
import com.team5.household.expense.Security.provider.JwtTokenProvider;
import com.team5.household.expense.Security.VO.TokenVO;
import com.team5.household.expense.vo.LoginVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class Token {
    private final MemberInfoRepository memberInfoRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final KaKaoMemberRepository kakaoMemberRepository;
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
        KaKaoMemberInfoEntity kakaoEntity = kakaoMemberRepository.findByEmail(email).orElseThrow();
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(kakaoEntity.getKmiSeq(), kakaoEntity.getEmail());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        
        return jwtTokenProvider.generateToken(authentication);
    }
}
