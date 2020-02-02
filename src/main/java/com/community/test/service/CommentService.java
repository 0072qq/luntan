package com.community.test.service;

import com.community.test.enums.CommentTypeEnum;
import com.community.test.exception.CustomizeException;
import com.community.test.mapper.CommentMapper;
import com.community.test.mapper.QuestionMapper;
import com.community.test.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;

    public void insert(Comment comment) {
        if(comment.getParentId() == null || comment.getParentId()==0){
            throw new CustomizeException(1001,"未针对具体问题进行评论");
        }

        if(comment.getType()==null || !CommentTypeEnum.isExist(comment.getType())){
            throw new CustomizeException(1002,"类型不明确");
        }

        if(comment.getType()==CommentTypeEnum.COMMENT.getType()){
            //回复评论
            Comment comment1 = commentMapper.selectByPrimaryKey(comment.getParentId());
            if(comment1==null){
                throw new CustomizeException(1004,"你回复的评论不存在");
            }
            commentMapper.insert(comment);
        }else {
            //回复问题
            commentMapper.insert(comment);
            Long parentId = comment.getParentId();
            int id = parentId.intValue();
            questionMapper.CommentAdd(id);
        }
    }
}
