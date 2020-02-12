package com.community.test.service;

import com.community.test.dto.CommentTranDTO;
import com.community.test.enums.CommentTypeEnum;
import com.community.test.enums.NotificationEnum;
import com.community.test.enums.NotificationStatusEnum;
import com.community.test.exception.CustomizeException;
import com.community.test.mapper.CommentMapper;
import com.community.test.mapper.NotificationMapper;
import com.community.test.mapper.QuestionMapper;
import com.community.test.mapper.UserMapper;
import com.community.test.model.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotificationMapper notificationMapper;

    @Transactional
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
            Notification notification = new Notification();
            notification.setGmtCreate(System.currentTimeMillis());
            notification.setNotifier(comment.getCommentator());
            notification.setOutid(comment.getParentId().intValue());
            notification.setReceiver(comment1.getCommentator());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setType(NotificationEnum.REPLY_COMMENT.getStatus());
            notificationMapper.insert(notification);
        }else {
            //回复问题
            commentMapper.insert(comment);
            Long parentId = comment.getParentId();
            int id = parentId.intValue();
            questionMapper.CommentAdd(id);
            Question byId = questionMapper.getById(comment.getParentId().intValue());
            Notification notification = new Notification();
            notification.setGmtCreate(System.currentTimeMillis());
            notification.setNotifier(comment.getCommentator());
            notification.setOutid(comment.getParentId().intValue());
            notification.setReceiver(byId.getCreater());
            notification.setStatus(NotificationStatusEnum.UNREAD.getStatus());
            notification.setType(NotificationEnum.REPlY_QUESTION.getStatus());
            notificationMapper.insert(notification);

        }
    }

    public List<CommentTranDTO> ListByQuestionId(Integer id, Integer type) {
        List<CommentTranDTO> dtos = new ArrayList<>();
        CommentExample commentExample = new CommentExample();
        long id1 = (long)id;
        commentExample.createCriteria()
                .andParentIdEqualTo(id1)
                .andTypeEqualTo(type);
        commentExample.setOrderByClause("gmt_create desc");
        List<Comment> comments1 = commentMapper.selectByExample(commentExample);

        if(comments1.size()==0){
            return new ArrayList<>();
        }

        for (Comment comment:comments1
             ) {
            CommentTranDTO commentTranDTO = new CommentTranDTO();
            BeanUtils.copyProperties(comment,commentTranDTO);
            User byId = userMapper.findById(comment.getCommentator());
            commentTranDTO.setUser(byId);
            dtos.add(commentTranDTO);
        }
        return dtos;
    }
}
