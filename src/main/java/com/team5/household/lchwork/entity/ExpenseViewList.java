package com.team5.household.lchwork.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.Immutable;

import jakarta.annotation.Generated;
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
@Immutable
@Table(name = "expense_list_view")
@DynamicInsert
@Builder
public class ExpenseViewList {
    @Column(name = "mi_seq") private Long miSeq;
    @Id
    @Column(name = "eh_seq") private Long ehSeq;
    @Column(name = "eh_date") private LocalDateTime ehDate;
    @Column(name = "eh_title") private String ehTitle;
    @Column(name = "eh_price") private Integer ehPrice;
    @Column(name = "eh_location") private String ehLocation;
    @Column(name = "eh_img_file") private String ehImgFile;
    @Column(name = "cc_seq") private Long ccSeq;
    @Column(name = "cc_name") private String ccName;
    @Column(name = "cdc_seq") private String cdcSeq;
    @Column(name = "cdc_name") private String cdcName;
    @Column(name = "pi_type") private Integer piType;
    @Column(name = "pi_name") private String piName;
}
