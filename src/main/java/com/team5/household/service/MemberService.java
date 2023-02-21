package com.team5.household.service;


import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.team5.household.entity.MemberInfoEntity;
import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.vo.membervo.LoginVO;
import com.team5.household.vo.membervo.MemberJoinVO;
import com.team5.household.vo.membervo.MemberListVO;
import com.team5.household.vo.membervo.MemberResponseVO;
import com.team5.household.vo.membervo.MemberUpdateVO;
import com.team5.household.vo.membervo.MemberUpdateResponseVO;
import com.team5.household.vo.membervo.UserUpdateVO;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final MemberInfoRepository infoRepository;
    
    //회원가입
    public MemberResponseVO joinMember(MemberJoinVO data) {
        MemberInfoEntity newMember = new MemberInfoEntity(data);
        infoRepository.save(newMember);
        MemberResponseVO response = new MemberResponseVO();
        response.setEmail(data.getEmail());
        response.setNickname(data.getNickname());
        return response;
    }
    //회원 로그인
    public MemberResponseVO loginMember(LoginVO data) {
        MemberInfoEntity user = infoRepository.findByEmailAndPwd(data.getEmail(), data.getPwd()).orElseThrow();
        MemberResponseVO userData = new MemberResponseVO();

        userData.setEmail(user.getEmail());
        userData.setNickname(user.getNickname());
        userData.setMessage("로그인 성공");

        return userData;
    }
    //회원정보 삭제
    public MemberUpdateResponseVO deleteMember(Long seq){
        MemberUpdateResponseVO response = new MemberUpdateResponseVO();
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
    //회원정보 전부 조회
    public MemberListVO listall(){
        MemberListVO response = new MemberListVO();
        List<MemberInfoEntity> memberList = infoRepository.findAll();

        if(memberList != null){
            response.setStatus(true);
            response.setMessage("회원정보 리스트 조회 성공");
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
