package com.caihyspace.controller;

import com.caihyspace.entity.User;
import com.caihyspace.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Slf4j
@RestController
@RequestMapping(value = "/users") //通过这里配置使下面的映射都在/users下
public class UserController {

    static Map<Integer, User> users = Collections.synchronizedMap(new HashMap<Integer, User>());

    @RequestMapping("/hello")
    public String hello() throws Exception {
        throw new Exception("发生错误");
    }

    @RequestMapping("/json")
    public String json() throws Exception {
        throw new CustomException("发生错误~~~~~~");
    }

    @GetMapping(value = "/")
    public List<User> getUserList() {
        // 处理"/users/"的GET请求，用来获取用户列表
        // 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
        List<User> list = new ArrayList<User>(users.values());
        log.info("get 方法/users");
        return list;
    }

    @PostMapping(value = "/")
    public String createUser(@ModelAttribute User user) {
        users.put(user.getId(), user);
        log.info("post /");
        return "success";
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable Long id) {
        return users.get(id);
    }

    @PutMapping(value = "/{id}")
    public String updateUser(@PathVariable Integer id, @ModelAttribute User user) {
        User u = users.get(id);
        u.setName(user.getName());
        u.setAge(user.getAge());
        users.put(id, u);
        return "success";
    }

    @DeleteMapping(value = "/{id}")
    public String deleteUserById(@PathVariable Long id) {
        users.remove(id);
        return "success";
    }

}
