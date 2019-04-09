package com.ccz.redisdemo.service.impl;

import com.ccz.redisdemo.entity.User;
import com.ccz.redisdemo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private Map<Long, User> userMap = new HashMap<>();

    public UserServiceImpl() {
        User u1 = new User();
        u1.setId(1L);
        u1.setName("1111");
        User u2 = new User();
        u2.setId(2L);
        u2.setName("2222");
        User u3 = new User();
        u3.setId(3L);
        u3.setName("3333");
        userMap.put(1L, u1);
        userMap.put(2L, u2);
        userMap.put(3L, u3);
    }

    @Override
    public List<Object> list() {
        return Arrays.asList(userMap.values().toArray());
    }

    @Override
    @Cacheable(value = "user", key = "'user'.concat(#id.toString())")
    public User findUserById(Long id) {
        log.info("findUserById ...." + id);
        return userMap.get(id);
    }

    @Override
    @CachePut(value = "user", key = "'user'.concat(#user.id.toString())")
    public void update(User user) {
        log.info("usermap.put user" + user.getId());
        userMap.put(user.getId(), user);
    }

    @Override
    @CacheEvict(value = "user", key = "'user'.concat(#id.toString())", beforeInvocation = true)
    public void remove(Long id) {
        userMap.remove(id);
    }

    @Override
    public User upuser(Long id) {
        return null;
    }
}
