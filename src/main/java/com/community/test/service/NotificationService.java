package com.community.test.service;

import com.community.test.dto.NotificationDTO;
import com.community.test.enums.NotificationEnum;
import com.community.test.enums.NotificationStatusEnum;
import com.community.test.mapper.CommentMapper;
import com.community.test.mapper.NotificationMapper;
import com.community.test.mapper.QuestionMapper;
import com.community.test.mapper.UserMapper;
import com.community.test.model.Notification;
import com.community.test.model.NotificationExample;
import com.community.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NotificationService {

    @Autowired
    NotificationMapper notificationMapper;
    @Autowired
    QuestionMapper questionMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CommentMapper commentMapper;

    public List<NotificationDTO> list(Integer id) {

        String title="";

        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andReceiverEqualTo(id);
        example.setOrderByClause("status");


        List<NotificationDTO> notificationDTOs = new ArrayList<>();

        List<Notification> notifications = notificationMapper.selectByExample(example);

        for (Notification notification:notifications) {
            Integer notifier = notification.getNotifier();
            NotificationDTO dto = new NotificationDTO();
            if(notification.getType()== NotificationEnum.REPlY_QUESTION.getStatus()){
                title = questionMapper.getById(notification.getOutid()).getTitle();
                dto.setType(NotificationEnum.REPlY_QUESTION.getName());
                dto.setQuestionId(0);
            }else {
                title = commentMapper.selectByPrimaryKey(Long.valueOf(notification.getOutid())).getDesc();
                dto.setType(NotificationEnum.REPLY_COMMENT.getName());
                dto.setQuestionId(commentMapper.selectByPrimaryKey(Long.valueOf(notification.getOutid())).getParentId().intValue());
            }
            dto.setGmtCreate(System.currentTimeMillis());
            dto.setOutTitle(title);
            dto.setOutid(notification.getOutid());
            dto.setStatus(notification.getStatus());
            dto.setId(notification.getId());
            User byId = userMapper.findById(notifier);
            dto.setNotifier(byId);
            notificationDTOs.add(dto);
        }
        return notificationDTOs;
    }

    public void update(int id){
        Notification notification = new Notification();
        notification.setStatus(NotificationStatusEnum.READ.getStatus());

        NotificationExample example = new NotificationExample();
        example.createCriteria()
                .andIdEqualTo(id);

        notificationMapper.updateByExampleSelective(notification,example);

    }
}
