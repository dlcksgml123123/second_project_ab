package com.team5.household.expense.Security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team5.household.expense.entity.MemberInfoEntity;
import com.team5.household.expense.repository.MemberInfoRepository;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {
    private final MemberInfoRepository memberInfoRepository;
    private final PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberInfoRepository.findByEmail(email).map(this::createUserDetails).orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }
    public UserDetails createUserDetails(MemberInfoEntity member) {
        return User.builder().username(member.getEmail())
                .password(passwordEncoder.encode(member.getPwd()))
                .roles(member.getMiRole())
                .build();
    }
}
