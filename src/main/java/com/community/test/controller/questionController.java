package com.community.test.controller;

import com.community.test.dto.QuestionDTO;
import com.community.test.mapper.QuestionMapper;
import com.community.test.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class questionController {

    @Autowired
    private questionService questionService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model){

        QuestionDTO questionDTO = questionService.getById(id);
        //阅读数++
        questionService.ViewAdd(id);

        model.addAttribute("questionDTO",questionDTO);
        return "question";
    }
}
