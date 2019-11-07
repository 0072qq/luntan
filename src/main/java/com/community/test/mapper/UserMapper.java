package com.community.test.mapper;

import com.community.test.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-07 16:34
 **/
@Mapper
public interface UserMapper {
    void insertUser(User user);
}
