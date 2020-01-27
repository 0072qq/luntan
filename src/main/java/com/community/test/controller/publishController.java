package com.community.test.controller;

import com.community.test.mapper.QuestionMapper;
import com.community.test.model.Question;
import com.community.test.model.User;
import com.community.test.service.questionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private questionService questionService;

    @RequestMapping(value = "/publish", method = RequestMethod.GET)
    public String publish(){
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(Question question, HttpServletRequest req,
                            Model model){
        //后台校验逻辑，避免前端校验被绕过
        String title = question.getTitle();
        String description = question.getDescription();
        String tag = question.getTag();

        model.addAttribute("title",title);
        model.addAttribute("description",description);
        model.addAttribute("tag",tag);
        if(title==null && "".equals(title)){
            model.addAttribute("error","标签不能为空");
            return "publish";
        }
        if(description==null && "".equals(description)){
            model.addAttribute("error","描述不能为空");
            return "publish";
        }


        User user = null;

        user = (User) req.getSession().getAttribute("user");
        System.out.println(user);

        if(user == null){
            model.addAttribute("error","用户未登录");
            return "publish";
        }

        question.setCreater(user.getId());
        questionService.createOrUpdate(question);

        return "redirect:/index";
    }

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Integer id,Model model){

        Question byId = questionMapper.getById(id);
        model.addAttribute("title",byId.getTitle());
        model.addAttribute("description",byId.getDescription());
        model.addAttribute("tag",byId.getTag());
        model.addAttribute("id",byId.getId());
        return "publish";
    }

}
