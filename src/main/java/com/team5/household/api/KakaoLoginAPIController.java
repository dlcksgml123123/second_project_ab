package com.team5.household.api;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.team5.household.service.KakaoAuthService;
import com.team5.household.vo.KakaoUserInfoVO;
import com.team5.household.vo.OAuthTokenVO;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor
public class KakaoLoginAPIController {
    private final KakaoAuthService authService;
    // https://kauth.kakao.com/oauth/authorize?client_id=ebe921cbeb961a1f8b1c320902aa8b36&redirect_uri=http://localhost:8585/oauth/kakao&response_type=code
    @GetMapping("/kakao")
    public String getKakaoCode(@RequestParam(value = "code") String code) {
        // 리디렉션에 의해서 토큰 내어줌
        OAuthTokenVO authTokenVO = authService.getAuthToken(code);
        return authTokenVO.getAccess_token();
    }

    @GetMapping("/kakaoauth")
    public ResponseEntity<Map<String, Object>> kakaoCallback(@RequestParam(value = "token") String token) throws Exception{
        // 프론트에서 받은 코드로 재차 요청
        Map<String, Object> map = new LinkedHashMap<>();
    // public ResponseEntity<Map<String, Object>> kakaoCallback(@RequestParam(value = "code") String code) throws Exception { // Data를 리턴해주는 컨트롤러 함수
    //     Map<String, Object> map = new LinkedHashMap<>();
    //     OAuthTokenVO authTokenVO = authService.getAuthToken(code);
    //     System.out.println(authTokenVO);
        
        /*
         * RestTemplate rt = new RestTemplate();
         * // HttpHeader 오브젝트 생성
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        // HttpBody 오브젝트 생성
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type" , "authorization_code");
        params.add("client_id" , "ebe921cbeb961a1f8b1c320902aa8b36");
        params.add("redirect_uri" , "http://localhost:8585/oauth/kakao");
        params.add("code", code);
        
        // HttpHeader와 HttpBody를 하나의 오브젝트에 담기
        HttpEntity<MultiValueMap<String, String>> kakaoTokenRequest = new HttpEntity<>(params, headers);

        // Http 요청하기 - POST방식으로 - 그리고 response 변수의 응답 받음
        ResponseEntity<String> response = rt.exchange("https://kauth.kakao.com/oauth/token", HttpMethod.POST, kakaoTokenRequest, String.class);
        
        ObjectMapper objectMapper = new ObjectMapper();
        OAuthTokenVO authTokenVO = null;
        try {
            authTokenVO = objectMapper.readValue(response.getBody(), OAuthTokenVO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("카카오 엑세스 토큰: "+authTokenVO.getAccess_token());
        
         */
                
        /*
         * RestTemplate rt2 = new RestTemplate();

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
        System.out.println(nickname);
        System.out.println(email);
         */
        KakaoUserInfoVO infoVO = authService.getKakaoUserData(token);
        // 카카오 사용자 정보 나옴
        map.put("userData", infoVO);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
