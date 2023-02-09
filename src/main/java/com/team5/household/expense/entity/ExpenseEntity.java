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

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Builder
@Table(name = "expense_history")
public class ExpenseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Schema(description = "지출 내역 번호", example = "1")
    @Column(name = "eh_seq")  private Long ehSeq;

    @Schema(description = "지출 제목", example = "20230209 영화관")
    @Column(name = "eh_title") private String ehTitle;

    @Schema(description = "지출 내용", example = "스파이더맨")
    @Column(name = "eh_content") private String ehContent;

    @Schema(description = "회원 번호", example = "1")
    @Column(name = "eh_mi_seq") private Long ehMiSeq;

    @Schema(description = "지출 내역 날짜", example = "2022-02-09 00:00:00")
    @Column(name = "eh_date") private @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDate ehDate;

    @Schema(description = "결제수단", example = "1")
    @Column(name = "eh_pi_seq") private Long ehPiSeq;

    @Schema(description = "결제금액", example = "10000")
    @Column(name = "eh_price") private Long ehPrice;

    @Schema(description = "가게 이름", example = "CGV")
    @Column(name = "eh_store_name") private String ehStoreName;

    @Schema(description = "지출 내역 이미지(무시해도됨)", example = "spiderman")
    @Column(name = "eh_img_url") private String ehImgUrl;

    @Schema(description = "지출 내역 이미지파일명", example = "spiderman")
    @Column(name = "eh_img_file") private String ehImgFile;

    @Schema(description = "가게 주소", example = "대구광역시 북구 연경동 동화천로 290")
    @Column(name = "eh_location") private String ehLocation;

    @Schema(description = "지출 금액", example = "20000")
    @Column(name = "eh_balance") private Long ehBalance;

    @Schema(description = "문화 대분류 번호", example = "1")
    @Column(name = "eh_cc_seq") private Long ehCcSeq;

    @Schema(description = "문화 소분류 번호", example = "2")
    @Column(name = "eh_cdc_seq") private Long ehCdcSeq;
}
