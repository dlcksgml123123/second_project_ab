package com.team5.household.lchwork.entity;

import java.util.Date;

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
@Builder
@Table(name = "expense_history")
public class LchExpenseHistoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "eh_seq") private Long ehSeq;
    @Column(name = "eh_title") private String ehTitle;
    @Column(name = "eh_content") private String ehContent;
    @Column(name = "eh_date") private Date ehDate;
    @Column(name = "eh_mi_seq") private Long ehMiSeq;
    @Column(name = "eh_pi_seq") private Long ehPiSeq;
    @Column(name = "eh_price") private Integer ehPrice;
    @Column(name = "eh_store_name") private String ehStoreName;
    @Column(name = "eh_img_url") private String ehImgUrl;
    @Column(name = "eh_img_file") private String ehImgFile;
    @Column(name = "eh_location") private String ehLocation;
    @Column(name = "eh_balance") private Integer ehBalance;
    @Column(name = "eh_cc_seq") private Long ehCcSeq;
    @Column(name = "eh_cdc_seq") private Long ehCdcSeq;
}
