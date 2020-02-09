package com.community.test.controller;

import com.community.test.dto.CommentDTO;
import com.community.test.dto.CommentTranDTO;
import com.community.test.dto.resultDTO;
import com.community.test.enums.CommentTypeEnum;
import com.community.test.model.Comment;
import com.community.test.model.User;
import com.community.test.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    @ResponseBody
    public Object post(@RequestBody CommentDTO commentDTO,
                       HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null){
            return resultDTO.errorOf(1001,"未登录不能进行评论");
        }
        Comment comment = new Comment();

        comment.setDesc(commentDTO.getDesc());
        comment.setParentId(commentDTO.getParentId());
        comment.setType(commentDTO.getType());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setGmtModified(comment.getGmtCreate());
        comment.setCommentator(user.getId());
        comment.setLikeCount(0L);
        commentService.insert(comment);

        return resultDTO.errorOf(2000,"已成功评论");
    }


    @GetMapping("/comment/{id}")
    @ResponseBody
    public resultDTO<List> comments(@PathVariable(name = "id")Long id, Model model){
        int id1 = id.intValue();
        List<CommentTranDTO> dtoss = commentService.ListByQuestionId(id1, CommentTypeEnum.COMMENT.getType());
        model.addAttribute("commentSub",dtoss);
        return resultDTO.okOf(dtoss);

    }
}
