package com.community.test.controller;

import com.community.test.components.InterceptorHandler;
import com.community.test.mapper.QuestionMapper;
import com.community.test.model.Question;
import com.community.test.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-16 22:18
 **/
@Controller
public class publishController {

    @Autowired
    private QuestionMapper questionMapper;

    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest req,
                            Model model){

        User user = null;

        user = (User) req.getSession().getAttribute("user");
        System.out.println(user);

        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        question.setCreater(user.getId());
        question.setGmt_create(System.currentTimeMillis());
        question.setGmt_modify(question.getGmt_create());


        questionMapper.create(question);
        return "redirect:/";
    }

}
