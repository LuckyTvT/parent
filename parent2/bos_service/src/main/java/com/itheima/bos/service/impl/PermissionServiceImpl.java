package com.itheima.bos.service.impl;

import com.itheima.bos.dao.PermissionDao;
import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    PermissionDao permissionDao;

    @Override
    public List<Permission> findAll() {
        return (List<Permission>)permissionDao.findAll();
    }

    @Override
    public Permission save(Permission model) {
        return permissionDao.save(model);
    }

    @Override
    public Permission findOne(Integer id) {
        return permissionDao.findOne(id);
    }

    @Override
    public List<Permission> findByRole(Role role) {
        return permissionDao.findByRole(role.getId());
    }
}
