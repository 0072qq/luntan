package com.community.test.controller;

import com.community.test.dto.CommentDTO;
import com.community.test.dto.resultDTO;
import com.community.test.mapper.CommentMapper;
import com.community.test.model.Comment;
import com.community.test.model.User;
import com.community.test.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

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
}
