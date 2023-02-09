package com.team5.household.service;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.team5.household.entity.MemberInfoEntity;
import com.team5.household.repository.MemberInfoRepository;
import com.team5.household.utils.AESAlgorithm;

@Service
public class MemberService {
    @Autowired MemberInfoRepository mRepo;
    
    public Map<String, Object> joinMember(MemberInfoEntity data) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        String emailPattern = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        String passwordPattern = "^[a-zA-Z\\d`~!@#$%^&*()-_=+]{6,}$";

        if (mRepo.countByMiEmail(data.getMiEmail()) == 1) {
            resultMap.put("status", false);
            resultMap.put("message", data.getMiEmail() + "은/는 이미 가입된 계정입니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        } else if (!Pattern.matches(passwordPattern, data.getMiEmail())) { // 공백없이 특수문자 가능 6자리 이상
            resultMap.put("status", false);
            resultMap.put("message", "비밀번호는 공백없이 6자리 이상 가능합니다.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        } else if (!Pattern.matches(emailPattern, data.getMiEmail())) {
            resultMap.put("status", false);
            resultMap.put("message", "올바른 이메일 형식이 아닙니다. 이메일을 다시 확인해주세요.");
            resultMap.put("code", HttpStatus.BAD_REQUEST);
        }  else {
            try {
                String encPwd = AESAlgorithm.Encrypt(data.getMiPwd());
                data.setMiPwd(encPwd);
            } catch (Exception e) {
                e.printStackTrace();
            }
            mRepo.save(data);
            resultMap.put("status", true);
            resultMap.put("message", "회원 가입이 완료되었습니다.");
            resultMap.put("code", HttpStatus.CREATED);
        }
        return resultMap;
    }
}