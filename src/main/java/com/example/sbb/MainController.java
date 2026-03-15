package com.example.sbb;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/sbb")
    public String index(){
        return "안녕하세요 sbb에 오신 것을 환영합니다.";
    }
    //루드 url매핑
    @GetMapping("/")
    public String root(){
        return "redirect:/question/list";
    }
}
