package com.community.test.mapper;

import com.community.test.model.Question;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-26 19:56
 **/
@Mapper
public interface QuestionMapper {

    void create(Question question);
}
