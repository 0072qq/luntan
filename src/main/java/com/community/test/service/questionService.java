package com.community.test.service;

import com.community.test.dto.QuestionDTO;
import com.community.test.dto.pageDTO;
import com.community.test.exception.CustomizeException;
import com.community.test.mapper.QuestionMapper;
import com.community.test.mapper.UserMapper;
import com.community.test.model.Question;
import com.community.test.model.User;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class questionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private pageDTO pageDTO;

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

    public List<QuestionDTO> getMyAll(Integer userId) {
        List<Question> all = questionMapper.getMyQuestion(userId);
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

    public pageDTO getPage(Integer page, Integer size) {
        pageDTO pageDTO = new pageDTO();

        Page<Object> objects = PageHelper.startPage(page, size, true);
        List<QuestionDTO> questions = getAll();
        PageInfo<QuestionDTO> pageInfo = new PageInfo<>(questions);

        System.out.println("****************");

        if(page>=objects.getPages()){
            page=objects.getPages();
        }
        if(page<=0){
            page=1;
        }

        pageDTO.setNowPage(page);
        pageDTO.setTotalPage(objects.getPages());
        pageDTO.setSinglePage(page,(int) objects.getPages());
        pageDTO.setQuestions(pageInfo.getList());

        return pageDTO;
    }

    public pageDTO list(Integer userId, Integer page, Integer size) {

        pageDTO pageDTO = new pageDTO();

        Page<Object> objects = PageHelper.startPage(page, size, true);
        List<QuestionDTO> questions =getMyAll(userId);
        PageInfo<QuestionDTO> pageInfo = new PageInfo<>(questions);

        System.out.println("****************");

        System.out.println(questions);
        if(page>=objects.getPages()){
            page=objects.getPages();
        }
        if(page<=0){
            page=1;
        }

        pageDTO.setNowPage(page);
        pageDTO.setTotalPage(objects.getPages());
        pageDTO.setSinglePage(page,(int) objects.getPages());
        pageDTO.setQuestions(pageInfo.getList());

        return pageDTO;
    }


    public QuestionDTO getById(Integer id) {
        Question question = questionMapper.getById(id);
        if (question == null){
            throw new CustomizeException("你找的问题不存在");
        }

        User user = userMapper.findById(question.getCreater());
        System.out.println(user);
        QuestionDTO dto = new QuestionDTO();
        BeanUtils.copyProperties(question,dto);
        dto.setUser(user);
        return dto;
    }

    public void createOrUpdate(Question question){
        if(question.getId()==null){
            question.setGmt_create(System.currentTimeMillis());
            question.setGmt_modify(question.getGmt_create());
            questionMapper.create(question);
        }else {

            question.setGmt_modify(question.getGmt_create());
            questionMapper.update(question);
        }
    }


    public int ViewAdd(Integer id) {
        int i = questionMapper.ViewAdd(id);
        if(i==0){
            return 0;
        }
        else return 1;
    }

    public List<QuestionDTO> getByTag(String tag, Integer id){
        List<Question> byTag = questionMapper.getByTag(tag);
        List<QuestionDTO> questionDTOS = new ArrayList<>();
        for (Question ques:byTag
             ) {
            if(ques.getId()!=id) {
                User user = userMapper.findById(ques.getCreater());
                QuestionDTO dto = new QuestionDTO();
                BeanUtils.copyProperties(ques, dto);
                dto.setUser(user);
                questionDTOS.add(dto);
            }
        }
        return questionDTOS;
    }
}
