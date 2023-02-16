package com.team5.household.expense.api;

import com.team5.household.expense.service.ExpenseService;
import com.team5.household.expense.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.awt.image.BufferedImage;

@RestController
@RequestMapping("/api/images")
@Tag(name = "파일 API", description = "이미지 출력 및 업로드")
public class FileAPIController {
    @Autowired FileService fService;
    @Autowired ExpenseService eService;
    @Operation(summary = "이미지 파일 출력", description = "특정 이미지를 검색합니다.")
    @GetMapping("/{filename}")
    public ResponseEntity<Resource> getImageFile  (
            @PathVariable String filename
    ) throws Exception{
        return eService.getImagedownload(filename);

    }

}
