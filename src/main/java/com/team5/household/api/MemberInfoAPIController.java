package com.team5.household.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.service.MemberService;
import com.team5.household.vo.memberVO.LoginVO;
import com.team5.household.vo.memberVO.MemberJoinVO;
import com.team5.household.vo.memberVO.MemberListVO;
import com.team5.household.vo.memberVO.MemberResponseVO;
import com.team5.household.vo.memberVO.MemberUpdateResponseVO;
import com.team5.household.vo.memberVO.MemberUpdateVO;
import com.team5.household.vo.memberVO.UserResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="회원등록정보", description="회원정보 CRUD API")
@RestController
@RequestMapping("/api/members")
public class MemberInfoAPIController {
    @Autowired MemberService mService;
    @Autowired MemberInfoRepository mRepo;
    //회원정보 등록
    @Operation(summary = "회원 정보 등록", description = "")
    @PostMapping("/join")
    public Map<String, Object> postMemberJoin(@RequestBody MemberJoinVO data){
        Map<String, Object> resultmap = new LinkedHashMap<>();
        MemberResponseVO response = mService.joinMember(data);
        resultmap.put("data", response);
        resultmap.put("status", HttpStatus.CREATED);
        return resultmap;
    }
    //로그인
    @PostMapping("/login")
    @Operation(summary = "회원 로그인", description = "email,pwd 사용")
    public ResponseEntity<MemberResponseVO> postMemberLogin(@RequestBody LoginVO login) {
        MemberResponseVO resultMap  = mService.loginMember(login);
        return new ResponseEntity<>(resultMap, HttpStatus.OK);
    }
    //회원정보 전체조회
    @Operation(summary = "회원 리스트 전체조회", description = "회원정보를 모두 조회합니다.")
    @GetMapping("/listall")
    public ResponseEntity<MemberListVO> getMemberListAll(){
        MemberListVO memberList = mService.listAll();
        return new ResponseEntity<>(memberList, HttpStatus.CREATED);
    }
    //회원정보 수정
    @Operation(summary = "회원정보 수정", description = "회원정보에 입력되지 않은 내용을 입력합니다.")
    @PostMapping("/update/{seq}")
    public ResponseEntity<MemberUpdateResponseVO> postMemberupdate(@PathVariable Long seq, @RequestBody MemberUpdateVO data) {
        MemberUpdateResponseVO response = mService.updateMember(seq, data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    //회원정보 삭제
    @Operation(summary = "회원정보 삭제", description = "url에 seq번호를 이용해서 회원의 내용을 삭제합니다.")
    @DeleteMapping("/delete/{seq}")
    public ResponseEntity<UserResponseVO> deleteMember( 
        @Parameter(description = "seq번호를 입력해서 삭제를 합니다.")
        @PathVariable Long seq){
       UserResponseVO response = mService.deleteMember(seq);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}

