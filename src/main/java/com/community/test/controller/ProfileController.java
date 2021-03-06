package com.community.test.controller;

import com.community.test.dto.NotificationDTO;
import com.community.test.dto.pageDTO;
import com.community.test.model.User;
import com.community.test.service.NotificationService;
import com.community.test.service.questionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    questionService questionService;
    @Autowired
    NotificationService notificationService;

    @GetMapping("/profile/{action}")
    public String profile(@PathVariable(name = "action")String action, Model model,
                          HttpServletRequest request, HttpServletResponse response,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "2") Integer size){

        User user = (User) request.getSession().getAttribute("user");
        if(user==null)
            return "redirect:/index";
        if("questions".equals(action)){
            model.addAttribute("section","questions");
            model.addAttribute("sectionName","我的提问");
            pageDTO list = questionService.list(user.getId(), page, size);
            model.addAttribute("myQuestion",list);
        }else if("replies".equals(action)){
            List<NotificationDTO> list = notificationService.list(user.getId());
            System.out.println(list);
            model.addAttribute("replies",list);
            model.addAttribute("section","replies");
            model.addAttribute("sectionName","最新回复");
        }
        return "profile";
    }

    @GetMapping("reply")
    public String update(HttpServletRequest request){
        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer outid = Integer.valueOf(request.getParameter("outid"));
        notificationService.update(id);
        User user = (User) request.getSession().getAttribute("user");
        List<NotificationDTO> list = notificationService.list(user.getId());
        System.out.println(list);

        return "forward:question/"+outid;
    }

}
