package com.community.test.controller;
import	java.util.ArrayList;

import com.community.test.mapper.UserMapper;
import com.community.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-01 17:35
 **/
@Controller
public class indexController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/index")
    public String Welcome(HttpServletRequest res, HttpServletResponse resp) {

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
        return "index";
    }
}
