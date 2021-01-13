package com.caihyspace.sharding.shardingspheredemo.dao;

import com.caihyspace.sharding.shardingspheredemo.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {


    void save(User user);

    List<User> selectAll();
}
