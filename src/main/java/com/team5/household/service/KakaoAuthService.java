package com.team5.household.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team5.household.config.KakaoValue;
import com.team5.household.entity.KakaoMemberInfoEntity;
import com.team5.household.repository.KakaoMemberRepository;
import com.team5.household.security.provider.JwtTokenProvider;
import com.team5.household.security.vo.TokenVO;
import com.team5.household.vo.KakaoNewUserInfoVO;
import com.team5.household.vo.KakaoUserInfoVO;
import com.team5.household.vo.OAuthTokenVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class KakaoAuthService {
    private final KakaoMemberRepository kakaoMemberRepository;
    private final Token token;
    
    public Boolean isDuplicatedEmail(String email){
        if(kakaoMemberRepository.countByEmail(email) > 0) {
            return true;
        }
        return false;
    }

    public OAuthTokenVO getAuthToken(String code){

        RestTemplate rt = new RestTemplate();

        // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", KakaoValue.CONTENT_TYPE);

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type" , KakaoValue.GRANT_TYPE);
        params.add("client_id" , KakaoValue.CLIENT_ID);
        params.add("redirect_uri" , KakaoValue.REDIRECT_URI);
        params.add("code", code);
        
        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response = rt.exchange(KakaoValue.RESPONSE_OAUTH, HttpMethod.POST, kakaoTokenRequest, String.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthTokenVO authTokenVO = null;
        try {
            authTokenVO = objectMapper.readValue(response.getBody(), OAuthTokenVO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return authTokenVO;
    }

    public KakaoUserInfoVO getKakaoUserData(OAuthTokenVO authTokenVO) throws ParseException{
        RestTemplate rt2 = new RestTemplate();
        
        // HttpHeader 오브젝트 생성
        HttpHeaders headers2 = new HttpHeaders();
        headers2.add("Authorization", "Bearer "+authTokenVO.getAccess_token());
        headers2.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
        
        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoProfileRequest2 = new HttpEntity<>(headers2);

        // Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response2 = rt2.exchange("https://kapi.kakao.com/v2/user/me", HttpMethod.POST, kakaoProfileRequest2, String.class);

        JSONParser parser = new JSONParser();
        JSONObject obj = (JSONObject)parser.parse(response2.getBody());
        JSONObject account = (JSONObject)obj.get("kakao_account");
        JSONObject profile = (JSONObject)account.get("profile");
        String nickname = (String)profile.get("nickname");
        String email = (String)account.get("email");

        
        if(isDuplicatedEmail(email)){
            KakaoUserInfoVO userData = new KakaoUserInfoVO(email, nickname);
            return userData;
        }
        else{
            KakaoUserInfoVO infoVO = new KakaoUserInfoVO(email, nickname);
            KakaoMemberInfoEntity kakaoUser = new KakaoMemberInfoEntity(infoVO);
            kakaoMemberRepository.save(kakaoUser);
            KakaoUserInfoVO userData = new KakaoUserInfoVO(email, nickname);
            return userData;
        }
    }
}
