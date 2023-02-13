package com.team5.household.expense.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Builder
@Table(name = "expense_history")
public class ExpenseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "eh_seq")  private Long ehSeq;

    @Column(name = "eh_title") private String ehTitle;

    @Column(name = "eh_content") private String ehContent;

    @Column(name = "eh_mi_seq") private Long ehMiSeq;

    @Column(name = "eh_date")
    private LocalDateTime ehDate;

    @Column(name = "eh_pi_seq") private Long ehPiSeq;

    @Column(name = "eh_price") private Long ehPrice;

    @Column(name = "eh_store_name") private String ehStoreName;

    @Column(name = "eh_img_url") private String ehImgUrl;

    @Column(name = "eh_img_file") private String ehImgFile;

    @Column(name = "eh_location") private String ehLocation;

    @Column(name = "eh_balance") private Long ehBalance;

    @Column(name = "eh_cc_seq") private Long ehCcSeq;

    @Column(name = "eh_cdc_seq") private Long ehCdcSeq;
}
