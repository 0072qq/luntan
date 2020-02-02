package com.community.test.mapper;

import com.community.test.dto.QuestionDTO;
import com.community.test.model.Question;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-26 19:56
 **/
@Mapper
public interface QuestionMapper {

    void create(Question question);

    List<Question> getAll();

    List<Question> getMyQuestion(Integer id);

    Question getById(Integer id);

    void update(Question question);

    int ViewAdd(Integer id);

    int CommentAdd(Integer id);
}
