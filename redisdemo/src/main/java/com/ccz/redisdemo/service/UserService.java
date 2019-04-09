package com.ccz.redisdemo.service;

import com.ccz.redisdemo.entity.User;

import java.util.List;

public interface UserService {

    List<Object> list();

    User findUserById(Long id);

    void update(User user);

    void remove(Long id);

    User upuser(Long id);

}
