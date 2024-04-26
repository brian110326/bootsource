package com.example.movie.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequestMapping("/upload")
public class UploadController {

    // fetch ==> @RestController

    @GetMapping("/ex1")
    public void getMethodName() {
        log.info("upload form 요청");
    }

    @PostMapping("/uploadAjax")
    public void postMethodName(MultipartFile[] uploadFiles) {

        for (MultipartFile multipartFile : uploadFiles) {
            String oriName = multipartFile.getOriginalFilename();
            String fileName = oriName.substring(oriName.lastIndexOf("\\") + 1);
            log.info("파일정보 - 전체경로 : {}", oriName);
            log.info("파일정보 - 파일명 : {}", fileName);
        }
    }

}
