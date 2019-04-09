package com.caihyspace.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MockServletContext.class)
@WebAppConfiguration
public class UserControllerTest {

    private MockMvc mvc;

    @Test
    public void getUserList() throws Exception {
    }

    @Test
    public void createUser() throws Exception {
    }

    @Test
    public void getUserById() throws Exception {
    }

    @Test
    public void updateUser() throws Exception {
    }

    @Test
    public void deleteUserById() throws Exception {
    }

}