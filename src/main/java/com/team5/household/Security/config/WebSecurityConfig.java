package com.team5.household.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.team5.household.security.filter.JwtAuthenticationFilter;
import com.team5.household.security.provider.JwtTokenProvider;

import lombok.RequiredArgsConstructor;

@Configuration // 직접 @Bean을 등록
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {
    private final JwtTokenProvider jwtTokenProvider;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        System.out.println("[LOG] =========== filterChain()");
         // Http basic Auth  기반으로 로그인 인증창이 뜸.  disable 시에 인증창 뜨지 않음. 
         // rest api이므로 csrf 보안이 필요없으므로 disable처리.
        http.httpBasic().disable().csrf().disable()
                // jwt token으로 인증하므로 stateless 하도록 처리.
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 요청에 대한 보안 설정을 시작함
                .and().authorizeHttpRequests()
                .requestMatchers("/*", "/api/members/join", "/swagger-ui/**", "/api/members/login", "/oauth/**").permitAll()
                .requestMatchers("/api/**").authenticated()
                .anyRequest().authenticated()
                .and()
                // JwtAuthenticationFilter를 UsernamePasswordAuthentictaionFilter 전에 적용
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
