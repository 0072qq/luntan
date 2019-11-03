package com.community.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-01 17:35
 **/
@Controller
public class indexController {

    @RequestMapping("/")
    public String Welcome() {
        return "index";
    }
}
