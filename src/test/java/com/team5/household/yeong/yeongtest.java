package com.team5.household.yeong;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.team5.household.entity.KakaoMemberInfoEntity;
import com.team5.household.entity.MemberInfoEntity;
import com.team5.household.repository.KakaoMemberRepository;
import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.security.provider.JwtTokenProvider;
import com.team5.household.security.vo.TokenVO;
import com.team5.household.service.Token;
import com.team5.household.vo.KakaoUserInfoVO;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class yeongtest {
    @Autowired KakaoMemberRepository kakaoMemberRepository;
    @Autowired MemberInfoRepository memberInfoRepository;
    @Autowired AuthenticationManagerBuilder authenticationManagerBuilder;
    @Autowired JwtTokenProvider jwtTokenProvider;
    @Autowired Token token;
    @Autowired PasswordEncoder passwordEncoder;

    @Test
	void KakaoToken() {


        String eamil = "kakaouser@naver.com";
        String nickname = "카카오로그인";
        KakaoUserInfoVO infoVO = new KakaoUserInfoVO(eamil, nickname);
        KakaoMemberInfoEntity kakaoEntity = new KakaoMemberInfoEntity(infoVO);
        kakaoMemberRepository.save(kakaoEntity);
        TokenVO tokenVO = token.getKakaoToken(kakaoEntity.getEmail());
        
        KakaoUserInfoVO userData = new KakaoUserInfoVO(eamil, nickname, tokenVO);
        // UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(kakaoEntity.getKmiSeq(), kakaoEntity.getEmail());
        // Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println(userData);
        // log.info(userData);
	}

    
    @Test
    @DisplayName("카카오 로그인시 토큰 발급")
    void getKakaoToken(){
        String email = "addi0518@naver.com";
        KakaoMemberInfoEntity kakaoEntity = kakaoMemberRepository.findByEmail(email);
        
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(kakaoEntity.getKmiSeq(), kakaoEntity.getEmail());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        log.info(email);
        // return jwtTokenProvider.generateToken(authentication);
    }

    @Test
    void KakaoMemberEntity(){
        String email = "addi0518@naver.com";
        KakaoMemberInfoEntity kakaoEntity = kakaoMemberRepository.findByEmail(email);
        log.info(kakaoEntity.getEmail());
    }

    @Test
    void Authen(){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(10, "addi0518@naver.com");
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    }

    @Test
    @Transactional
    void getToken() {
        MemberInfoEntity entity = memberInfoRepository.findByEmailAndPwd("user01@email.com", "123456").get();
        System.out.println(entity.getMiRole());
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(entity.getMiSeq(), entity.getPwd());
        System.out.println(authenticationToken);

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        
        System.out.println(authentication);
        // return jwtTokenProvider.generateToken(authentication);
    }
}
