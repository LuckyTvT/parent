package com.itheima.bos.service;

import com.itheima.bos.domain.system.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    void save(Role model, String permissionIds, String menuIds);

}
