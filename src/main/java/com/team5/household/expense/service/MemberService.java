package com.team5.household.expense.service;

import com.team5.household.expense.vo.memberVO.*;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.team5.household.expense.entity.MemberInfoEntity;
import com.team5.household.expense.repository.MemberInfoRepository;
import com.team5.household.expense.Security.provider.JwtTokenProvider;
import com.team5.household.expense.Security.VO.TokenVO;

import lombok.RequiredArgsConstructor;

import java.util.List;

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
        MemberResponseVO response = new MemberResponseVO();
        response.setEmail(newMember.getEmail());
        response.setNickname(newMember.getNickname());
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
        memberData.setSeq(member.getMiSeq());
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
