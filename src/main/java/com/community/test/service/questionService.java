package com.community.test.service;

import com.community.test.dto.QuestionDTO;
import com.community.test.mapper.QuestionMapper;
import com.community.test.mapper.UserMapper;
import com.community.test.model.Question;
import com.community.test.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class questionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public List<QuestionDTO> getAll() {
        List<Question> all = questionMapper.getAll();
        System.out.println(all);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question question:all
             ) {
            User user = userMapper.findById(question.getCreater());
            System.out.println(user);
            QuestionDTO dto = new QuestionDTO();
            BeanUtils.copyProperties(question,dto);
            dto.setUser(user);
            questionDTOS.add(dto);
        }
        return questionDTOS;
    }
}
