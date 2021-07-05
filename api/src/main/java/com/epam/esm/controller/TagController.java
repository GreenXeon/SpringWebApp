package com.epam.esm.controller;

import com.epam.esm.dao.TagDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tag")
public class TagController {

    private final TagDAO tagDAO;

    @Autowired
    public TagController(TagDAO tagDAO) {
        this.tagDAO = tagDAO;
    }

    @GetMapping("/sample")
    public String saySample(){
        return "new";
    }
}
