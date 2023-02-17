package com.team5.household.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.service.MemberService;
import com.team5.household.vo.LoginVO;
import com.team5.household.vo.MemberJoinVO;
import com.team5.household.vo.responsevo.MemberResponseVO;
import com.team5.household.vo.responsevo.UserResponseVO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="회원등록정보", description="회원정보 CRUD API")
@RestController
@RequestMapping("/api/members")
public class MemberInfoAPIController {
    @Autowired MemberService mService;
    @Autowired MemberInfoRepository mRepo;
    @Operation(summary = "회원 정보 등록", description = "")
    //회원정보 등록
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
    @Operation(summary = "회원 로그인", description = "")
    public Map<String, Object> postMemberLogin(@RequestBody LoginVO login) {
        Map<String, Object> resultMap = mService.loginMember(login);
        return resultMap;
    }
    //회원정보 조회
    
    //회원정보 수정
    // @PostMapping("/update{seq}")
    // public ResponseEntity<Object> postMemberupdate(@PathVariable Long seq, @RequestBody UserUpdateVO userUpdate) {
    // Map<String, Object> map = new LinkedHashMap<>();
    // map = mService.updateMember();
    // return new ResponseEntity<>(map, (HttpStatus) map.get("code"));
    // }
    //회원정보 삭제
    @DeleteMapping("/delete/{seq}")
    public ResponseEntity<UserResponseVO> deleteMember( 
        @Parameter(description = "seq번호를 입력해서 삭제를 합니다.")
        @PathVariable Long seq){
       UserResponseVO response = mService.deleteMember(seq);
       return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
}

