package com.community.test.controller;

import com.community.test.dto.CommentTranDTO;
import com.community.test.dto.QuestionDTO;
import com.community.test.enums.CommentTypeEnum;
import com.community.test.service.CommentService;
import com.community.test.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class questionController {

    @Autowired
    private questionService questionService;
    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id") Integer id, Model model){

        QuestionDTO questionDTO = questionService.getById(id);
        //阅读数++
        questionService.ViewAdd(id);

        List<CommentTranDTO> dtos = commentService.ListByQuestionId(id, CommentTypeEnum.QUESTION.getType());

        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("commentDTO",dtos);
        return "question";
    }
}
