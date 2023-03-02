package com.team5.household.expense.service;

import com.team5.household.expense.vo.memberVO.LoginVO;
import com.team5.household.expense.vo.memberVO.MemberJoinVO;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.team5.household.expense.entity.MemberInfoEntity;
import com.team5.household.expense.repository.MemberInfoRepository;
import com.team5.household.expense.Security.provider.JwtTokenProvider;
import com.team5.household.expense.Security.VO.TokenVO;
import com.team5.household.expense.vo.memberVO.MemberResponseVO;

import jakarta.transaction.Transactional;
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

    @Transactional
    public MemberResponseVO joinMember(MemberJoinVO data) throws Exception {
        if(isDuplicatedEmail(data.getEmail())){
            throw new Exception("중복된 이메일");
        }
        MemberInfoEntity newMember = new MemberInfoEntity(data);
        infoRepository.save(newMember);
        MemberInfoEntity member = infoRepository.findByEmailAndPwd(data.getEmail(), data.getPwd()).orElseThrow();
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(member.getEmail(), member.getPwd(), member.getAuthorities());
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        TokenVO tokenVO = jwtTokenProvider.generateToken(authentication);
        MemberResponseVO response = new MemberResponseVO();
        response.setEmail(data.getEmail());
        response.setNickname(data.getNickname());
        response.setToken(tokenVO);
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
