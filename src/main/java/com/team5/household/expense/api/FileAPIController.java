package com.team5.household.expense.api;

import com.team5.household.expense.repository.ExpenseRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
public class FileAPIController {
    @Value("${file.image.expense}") String event_img_path;

    @Autowired ExpenseRepository eRepo;

    public ResponseEntity<Object> getImagedownload(
            @PathVariable String file, HttpServletRequest request,
            @PathVariable String type
    ) throws Exception
    {
        Map<String, Object> map = new LinkedHashMap<>();
        Path folderLocation = null;
        if (type.equals("event")) {
            folderLocation = Paths.get(event_img_path);
        }

        else {
            map.put("status", false);
            map.put("message", "타입이 올바르지 않습니다. (예시 : menu, store)");
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        String ext = "jpg";
        String exportName = file + "." + ext;
        Path targetFile = folderLocation.resolve(exportName);
        Resource r = null;
        try {
            r = new UrlResource(targetFile.toUri());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(r.getFile().getAbsolutePath());
            if (contentType == null) {
                contentType = "application/octet-stream";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ResponseEntity.ok()
                // 응답의 코드를 200 OK로 설정하고  산출한 타입을 응답에 맞는 형태로 변환
                .contentType(MediaType.parseMediaType(contentType))
                // 내보낼 내용의 타입을 설정 (파일),  attachment; filename=\""+r.getFilename()+"\" 요청한 쪽에서 다운로드 한 파일의 이름을 결정
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(contentType, "UTF-8") + "\"")
                .body(r);
        // 변환된 파일을 ResponseEntity에 추가
    }
}