package com.team5.household.expense.service;

import com.team5.household.expense.entity.ExpenseEntity;
import com.team5.household.expense.repository.ExpenseRepository;
import com.team5.household.expense.vo.ExpenseListResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class ExpenseService {
    @Autowired ExpenseRepository eRepo;

    @Value("${file.image.expense}") String expense_img_path;

//    public /*Map<String, Object>*/ ExpenseListResponseVO getExpenseList() {
//        return getExpenseList();
//    }

    public void addEvent(
        String ehTitle,
        LocalDateTime ehDate,
        Long ehMiSeq,
        Long ehPiSeq,
        Long ehPrice,
        String ehStoreName,
        @Nullable MultipartFile ehImgFile,
        @Nullable String ehContent,
        String ehLocation,
        Long ehBalance,
        Long ehCcSeq,
        Long ehCdcSeq
    ){
        Calendar c = Calendar.getInstance();
        Path eventFolderLocation = Paths.get(expense_img_path);

        String eventOriginFileName = ehImgFile.getOriginalFilename();
        String[] iFile = eventOriginFileName.split(("\\."));
        String iExt = iFile[iFile.length-1];
        String iFileName = "";
        for(int i=0;i<iFile.length-1;i++){
            iFileName += iFile[i];
        }
        String saveEventFileName = "Event"+"_";
        saveEventFileName+=c.getTimeInMillis()+"."+iExt;
        Path eventTargetFile = eventFolderLocation.resolve(ehImgFile.getOriginalFilename());

        try {
            Files.copy(ehImgFile.getInputStream(), eventTargetFile, StandardCopyOption.REPLACE_EXISTING);
        }   catch(Exception e){e.printStackTrace();}

        ExpenseEntity expense = ExpenseEntity.builder()
                .ehTitle(ehTitle)
                .ehContent(ehContent)
                .ehDate(ehDate)
                .ehMiSeq(ehMiSeq)
                .ehPiSeq(ehPiSeq)
                .ehPrice(ehPrice)
                .ehStoreName(ehStoreName)
                .ehImgUrl(iFileName)
                .ehLocation(ehLocation)
                .ehBalance(ehBalance)
                .ehCcSeq(ehCcSeq)
                .ehCdcSeq(ehCdcSeq)
                .ehImgFile(saveEventFileName).build();

        expense = eRepo.save(expense);
    }

    public Map<String, Object> getExpenseList(Long ehMiSeq, Pageable pageable) {
        Page<ExpenseEntity> page = eRepo.findPageByehMiSeq(ehMiSeq, pageable);
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("list", page.getContent());
        map.put("total", page.getTotalElements());
        map.put("totalPage", page.getTotalPages());
        map.put("currentPage", page.getNumber());
        return map;
    }

}
