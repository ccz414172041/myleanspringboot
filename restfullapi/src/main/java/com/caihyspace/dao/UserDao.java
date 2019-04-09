package com.caihyspace.dao;

import com.caihyspace.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User,Integer>{



}
