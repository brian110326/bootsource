package com.example.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.rest.dto.SampleDto;

@Controller
public class BasicController {

    @GetMapping("/basic")
    @ResponseBody // return 값이 template이 아닌 데이터
    public String getMethodName() {
        return "Hello";
    }

    // ResponseEntity(일반 controller에서도 return 타입이 데이터임을 의미) : 따로 @ResponseBody 필요 없음
    @GetMapping(value = "/check2", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SampleDto> getCheck2(double height, double weight) {
        SampleDto dto = new SampleDto();
        dto.setMno(1L);
        dto.setFirstName(String.valueOf(height));
        dto.setLastName(String.valueOf(weight));

        if (height < 150) {
            return new ResponseEntity<SampleDto>(dto, HttpStatus.BAD_REQUEST);
        } else {
            // 200 : OK
            return new ResponseEntity<SampleDto>(dto, HttpStatus.OK);
        }

    }

}
