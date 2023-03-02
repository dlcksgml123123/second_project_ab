package com.team5.household.expense.vo;

import com.team5.household.expense.entity.ExpenseEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data

public class ExpenseListResponseVO {
    private Long eh_seq;
    private String eh_title;
    private String eh_content;
    private Long eh_mi_seq;
    private LocalDateTime eh_date;
    private Long eh_pi_seq;
    private Long eh_price;
    private String eh_store_name;
    private String eh_img_file;
    private String eh_location;
    private Long eh_balance;
    private Long eh_cc_seq;
    private Long eh_cdc_seq;


    public ExpenseListResponseVO(ExpenseEntity entity) {
        this.eh_seq = entity.getEhSeq();
        this.eh_title = entity.getEhTitle();
        this.eh_content = entity.getEhContent();
        this.eh_mi_seq = entity.getEhMiSeq();
        this.eh_date = entity.getEhDate();
        this.eh_pi_seq = entity.getEhPiSeq();
        this.eh_price = entity.getEhPrice();
        this.eh_store_name = entity.getEhStoreName();
        this.eh_img_file = entity.getEhImgFile();
        this.eh_location = entity.getEhLocation();
        this.eh_balance = entity.getEhBalance();
        this.eh_cc_seq = entity.getEhCcSeq();
        this.eh_cdc_seq = entity.getEhCdcSeq();
    }
}
