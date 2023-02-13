package com.team5.household.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.team5.household.vo.MemberJoinVO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="member_info")
public class MemberInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mi_seq") private Long miSeq;
    @Column(name="mi_email") private String miEmail;
    @JsonIgnore
    @Column(name="mi_pwd") private String miPwd;
    @Column(name="mi_nickname") private String miNickname;
    // @Column(name="mi_name") private String miName;
    // @Column(name="mi_birth") private Date miBirth;
    // @Column(name="mi_gen") private Integer miGen;
    // @Column(name="mi_job") private String miJob;

    @Builder
    public MemberInfoEntity(MemberJoinVO data){
        this.miEmail = data.getMemberEmail();
        this.miPwd = data.getMemberPwd();
        this.miNickname = data.getMemberPwd();
    }
}
