package com.example.bootheat.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ForwardController {
    // .(확장자) 없는 모든 경로를 index.html로 포워딩(다단계 경로 포함)
    @RequestMapping({"/{path:[^\\.]*}", "/**/{path:[^\\.]*}"})
    public String forward() {
        return "forward:/index.html";
    }
}
