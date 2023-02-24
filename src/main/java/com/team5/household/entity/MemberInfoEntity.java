package com.team5.household.entity;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.household.vo.MemberJoinVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="member_info")
public class MemberInfoEntity implements UserDetails{
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mi_seq") private Long miSeq;
    @Column(name="mi_email") private String email;
    @Column(name="mi_pwd") private String pwd;
    @Column(name="mi_nickname") private String nickname;
    @Column(name="mi_role") private String miRole;

    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority(this.miRole));
        return roles;
    }
    
    @Builder
    public MemberInfoEntity(MemberJoinVO data){
        this.email = data.getEmail();
        this.pwd = data.getPwd();
        this.nickname = data.getNickname();
    }

    @JsonIgnore
    @Override
    public String getPassword() {
        return pwd;
    }

    @JsonIgnore
    @Override
    public String getUsername() {
        return email;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        throw new UnsupportedOperationException("Unimplemented method 'isEnabled'");
    }

}
