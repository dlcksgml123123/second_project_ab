package com.team5.household.expense.entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.household.expense.vo.memberVO.MemberJoinVO;
import com.team5.household.expense.vo.memberVO.MemberUpdateVO;

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
import org.hibernate.annotations.ColumnDefault;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
    @Column(name="mi_name") private String name;
    @Column(name="mi_birth") private LocalDate birth;
    @Column(name="mi_gen") private Integer gen;
    @Column(name="mi_job") private String job;
    @Column(name="mi_role") @ColumnDefault("USER") private String miRole;

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
        this.miRole = data.getRole();
    }
    
    public void setMemberInfo(MemberUpdateVO data){
        this.name = data.getName();
        this.nickname = data.getNickname();
        this.birth = data.getBirth();
        this.gen = data.getGen();
        this.job = data.getJob();
        this.miRole = data.getMiRole();
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
