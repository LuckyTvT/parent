package com.itheima.bos.service;

import com.itheima.bos.domain.system.User;

public interface LoginService {


    User login(String username, String password);
}
