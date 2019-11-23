package com.community.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-16 22:18
 **/
@Controller
public class publishController {

    @RequestMapping("/publish")
    public String publish(){
        return "publish";
    }
}
