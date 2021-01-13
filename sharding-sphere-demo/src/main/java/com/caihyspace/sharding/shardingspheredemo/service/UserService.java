package com.caihyspace.sharding.shardingspheredemo.service;

import com.caihyspace.sharding.shardingspheredemo.dao.UserMapper;
import com.caihyspace.sharding.shardingspheredemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public void save(User entity) {
        userMapper.save(entity);
    }

    public List<User> getUserList() {
        return userMapper.selectAll();
    }

}
