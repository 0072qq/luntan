package com.community.test.mapper;

import com.community.test.model.User;
import com.community.test.model.UserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program: TestPro
 * @author: WolfSky
 * @create: 2019-11-07 16:34
 **/
@Mapper
public interface UserMapper {
    void insertUser(User user);

    User findByToken(String token);

    User findById(Integer creater);

    User findByAccountId(String account_id);

    void updateUser(User dbUser);

    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
