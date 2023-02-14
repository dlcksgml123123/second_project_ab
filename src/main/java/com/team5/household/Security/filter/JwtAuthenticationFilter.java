package com.team5.household.Security.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import com.team5.household.Security.provider.JwtTokenProvider;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends GenericFilterBean{
    private final JwtTokenProvider jwtTokenProvider;
    // doFilter()의 종료시점에 Filter -> API Controller 로 Request와 Response가 전달
    // JWT 토큰을 받고, 내어보내는 endpoint
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        // request Header에서 토큰 추출
        String token = resolveToken((HttpServletRequest) request);
        if(token != null && jwtTokenProvider.validateToken(token)){
            Authentication authentication = jwtTokenProvider.getAuthentication(token);
            // SecurityContextHolder는 Authentication 객체가 저장되는 보관소
            // 일반적으로는 ThreadLocal 에 저장
            // 덕분에 코드 어디서나 Authentication 을 꺼내서 사용가능
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        // 스프링의 나머지 FilterChain들을 수행할 수 있도록 doFilter(request,response)를 호출
        // 모든 요청은 FilterChainProxy를 거치며 그 안에는 doFilter를 거침
        // doFilter를 호출하게 되고 doFilter는 doFilterInternal를 호출하게 된다. 이 곳에서 실질적인 filter 로직이 실행
        // doFilter는 doFilterInternal를 호출
        chain.doFilter(request, response);
    }
    
    // Header에서 토큰을 가져옴
    private String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authentication");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer")){
            return bearerToken.substring(7);
        }
        return null;
    }
}
