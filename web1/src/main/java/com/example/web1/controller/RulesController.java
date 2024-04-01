package com.example.web1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.web1.dto.RulesDto;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Log4j2
@RequestMapping("/calc")
public class RulesController {

    @GetMapping("/rules")
    public void rules() {
        log.info("/calc/rules 주소 요청");
    }

    @PostMapping("/rules")
    public String rulesPost(RulesDto dto, @ModelAttribute("op") String op, Model model) {
        log.info("num1 : {}", dto.getNum1());
        log.info("operation : {}", op);
        log.info("num2 : {}", dto.getNum2());

        model.addAttribute("operation", op);

        if (op.equals("+")) {
            model.addAttribute("result", dto.getNum1() + dto.getNum2());
        } else if (op.equals("-")) {
            model.addAttribute("result", dto.getNum1() - dto.getNum2());
        } else if (op.equals("*")) {
            model.addAttribute("result", dto.getNum1() * dto.getNum2());
        } else if (op.equals("/")) {
            model.addAttribute("result", dto.getNum1() / dto.getNum2());
        }

        return "/calc/result";
    }

}
