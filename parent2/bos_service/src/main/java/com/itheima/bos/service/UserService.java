package com.itheima.bos.service;

import com.itheima.bos.domain.system.User;

import java.util.List;

public interface UserService {
    void save(String roleIds, User model);

    List<User> findAll();

}
