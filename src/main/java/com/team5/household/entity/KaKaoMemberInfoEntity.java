package com.team5.household.entity;

import com.team5.household.vo.KakaoUserInfoVO;

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
@Table(name="kakaomember_info")
public class KaKaoMemberInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="kmi_seq") private Long kmiSeq;
    @Column(name="kmi_email") private String email;
    @Column(name="kmi_nickname") private String nickname;

    @Builder
    public KaKaoMemberInfoEntity(KakaoUserInfoVO data){
        this.email = data.getEmail();
        this.nickname = data.getNickname();
    }
}