package com.team5.household.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="member_info")
public class MemberEntity {
    @Id @GeneratedValue(strategy =  GenerationType.IDENTITY)
    @Column(name="mi_seq") private Long miSeq;
    @Column(name="mi_name") private String miName;
    @Column(name="mi_birth") private Date miBirth;
    @Column(name="mi_nickname") private String miNickName;
    @Column(name="mi_gen") private int miGen;
    @Column(name="mi_job") private String miJob;
    @Column(name="mi_email") private String miEmail;
    @Column(name="mi_pwd") private String miPwd;
}
