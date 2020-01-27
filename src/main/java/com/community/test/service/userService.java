package com.community.test.service;

import com.community.test.mapper.UserMapper;
import com.community.test.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class userService {

    @Autowired
    private UserMapper userMapper;


    public void createrUpdate(User u) {

        User dbUser = userMapper.findByAccountId(u.getAccount_id());
        if(dbUser == null){
            u.setGmt_create(System.currentTimeMillis());
            u.setGmt_modified(u.getGmt_create());
            userMapper.insertUser(u);
        }else {
            dbUser.setToken(u.getToken());
            dbUser.setAvator_url(u.getAvator_url());
            dbUser.setUsername(u.getUsername());
            dbUser.setGmt_modified(System.currentTimeMillis());
            userMapper.updateUser(dbUser);
        }

    }
}
