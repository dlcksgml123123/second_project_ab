package com.team5.household.expense.service;

import java.util.List;

import com.team5.household.expense.Security.provider.JwtTokenProvider;
import com.team5.household.expense.repository.MemberInfoRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import com.team5.household.expense.entity.MemberInfoEntity;
import com.team5.household.expense.vo.memberVO.LoginVO;
import com.team5.household.expense.vo.memberVO.MemberJoinVO;
import com.team5.household.expense.vo.memberVO.MemberResponseVO;
import com.team5.household.expense.vo.memberVO.MemberUpdateResponseVO;
import com.team5.household.expense.vo.memberVO.MemberUpdateVO;
import com.team5.household.expense.vo.memberVO.MemberListVO;
import com.team5.household.expense.vo.memberVO.UserResponseVO;
import com.team5.household.expense.Security.VO.TokenVO;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberInfoRepository infoRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    //회원가입
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
    //회원 로그인
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
    //회원정보 삭제
    public UserResponseVO deleteMember(Long seq){
        UserResponseVO response = new UserResponseVO();
        if(infoRepository.countByMiSeq(seq) != 0){
            infoRepository.deleteById(seq);
            response.setStatus(true);
            response.setMessage("선택한 회원의 정보가 삭제되었습니다.");
        }else{
            response.setStatus(false);
            response.setMessage("삭제 할 회원이 없습니다.");
        }
        return response;
    }
    //회원 리스트 전부 조회
    public MemberListVO listAll(){
        MemberListVO response = new MemberListVO();
        List<MemberInfoEntity> memberList = infoRepository.findAll();
        if(memberList != null){
            response.setStatus(true);
            response.setMessage("회원 리스트 조회 성공");
            response.setMemberList(memberList);
        }else{
            response.setStatus(false);
            response.setMessage("조회할 리스트가 없습니다.");
        }
        return response;    
    }
    //회원정보 수정
    public MemberUpdateResponseVO updateMember(Long seq, MemberUpdateVO data){
        MemberUpdateResponseVO response = new MemberUpdateResponseVO();
        MemberInfoEntity member = infoRepository.findByMiSeq(seq).orElseThrow();
        response.setData(data);
        member.setMemberInfo(data);
        infoRepository.save(member);
        response.setStatus(true);
        response.setMessage("회원정보가 변경되었습니다.");
        return response;
    }
}
