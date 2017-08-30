package com.itheima.bos.dao;

import com.itheima.bos.domain.system.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoginDao extends JpaRepository<User,Integer>{

    User findByUsernameAndPassword(String username, String password);
}
