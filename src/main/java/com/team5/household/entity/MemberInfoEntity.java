package com.team5.household.entity;

import java.time.LocalDate;
import java.util.Date;

import com.team5.household.vo.membervo.MemberJoinVO;
import com.team5.household.vo.membervo.MemberUpdateVO;

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
@Builder
public class MemberInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mi_seq") private Long miSeq;
    @Column(name="mi_email") private String email;
    @Column(name="mi_pwd") private String pwd;
    @Column(name="mi_nickname") private String nickname;
    @Column(name="mi_name") private String name;
    @Column(name="mi_birth") private LocalDate birth;
    @Column(name="mi_gen") private Integer gen;
    @Column(name="mi_job") private String job;

    @Builder
    public MemberInfoEntity(MemberJoinVO data){
        this.email = data.getEmail();
        this.pwd = data.getPwd();
        this.nickname = data.getNickname();
    }

    public void setMemberInfo(MemberUpdateVO data){
        this.nickname = data.getNickname();
        this.birth = data.getBirth();
        this.gen = data.getGen();
        this.job = data.getJob();
    }
}
