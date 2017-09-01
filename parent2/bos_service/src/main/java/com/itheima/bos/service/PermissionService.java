package com.itheima.bos.service;

import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.domain.system.Role;

import java.util.List;

public interface PermissionService {
    List<Permission> findAll();

    Permission save(Permission model);

    Permission findOne(Integer id);

    List<Permission> findByRole(Role role);
}
