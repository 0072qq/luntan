package com.community.test.controller;
import	java.util.ArrayList;

import com.community.test.dto.QuestionDTO;
import com.community.test.mapper.QuestionMapper;
import com.community.test.mapper.UserMapper;
import com.community.test.model.Question;
import com.community.test.model.User;
import com.community.test.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-01 17:35
 **/
@Controller
public class indexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private questionService service;

    @RequestMapping("/index")
    public String Welcome(HttpServletRequest res, HttpServletResponse resp,
                            Model model) {

//        Cookie[] cookies = res.getCookies();
//        if(cookies==null){
//            System.out.println("no cookie");
//            return "index";
//        }
//        else{
//            for (Cookie cookie:
//                    cookies) {
//                if("token".equals(cookie.getName())){
//                    String token = cookie.getValue();
//                    User user = userMapper.findByToken(token);
//                    if(user!=null){
//                        res.getSession().setAttribute("user",user);
//                        System.out.println(user);
//                    }
//                    break;
//                }
//            }
//        }

        System.out.println("-----------------------");
        List<QuestionDTO> questions = service.getAll();
        model.addAttribute("questionList",questions);
        System.out.println(questions);
        return "index";
    }
}
