package com.team5.household.lchwork.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "culture_detail_category")
public class LchCultureDetailCategoryEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "소분류 카테고리 번호", example = "11")
    @Column(name = "cdc_seq") private Long cdcSeq;
    @Schema(description = "소분류 카테고리 이름", example = "오케스트라")
    @Column(name = "cdc_name") private String cdcName;
    @Schema(description = "대분류 카테고리 번호", example = "2")
    @Column(name = "cdc_cc_seq") private Long cdcCcSeq;
    // @ManyToOne
    // @JoinColumn(name = "cc_seq") Long cdcCcSeq;
}
