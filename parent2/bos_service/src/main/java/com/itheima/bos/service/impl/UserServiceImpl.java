package com.itheima.bos.service.impl;

import com.itheima.bos.dao.RoleDao;
import com.itheima.bos.dao.UserDao;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    @Override
    public void save(String roleIds, User model) {
        userDao.save(model);
        if(roleIds!=null&&!roleIds.equals("")){
            Set<Role> roleSet = model.getRoles();
            String[] roles = roleIds.split(",");
            for (int i = 0; i < roles.length; i++) {
                Integer j = Integer.parseInt(roles[i]);
                Role role = roleDao.findOne(j);
                roleSet.add(role);
            }
        }
    }

    @Override
    public List<User> findAll() {
        return (List<User>)userDao.findAll();
    }
}
