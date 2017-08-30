package com.itheima.bos.service.impl;

import com.itheima.bos.dao.LoginDao;
import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Password;


@Service
@Transactional
public class LoginServiceImpl implements LoginService {

        @Autowired
    private LoginDao loginDao;

    @Override
    public User login(String username,String password) {
        User user = loginDao.findByUsernameAndPassword(username, password);
        return user;
    }

}
