package com.epam.esm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HSampleController {

    @GetMapping("/hello")
    public String saySample(){
        return "hello";
    }
}
