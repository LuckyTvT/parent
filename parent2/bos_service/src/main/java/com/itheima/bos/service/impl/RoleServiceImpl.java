package com.itheima.bos.service.impl;

import com.itheima.bos.dao.MenuDao;
import com.itheima.bos.dao.PermissionDao;
import com.itheima.bos.dao.RoleDao;
import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleDao roleDao;
    @Autowired
    PermissionDao permissionDao;
    @Autowired
    MenuDao menuDao;

    @Override
    public List<Role> findAll() {
        return (List<Role>)roleDao.findAll();
    }

    @Override
    public void save(Role model, String permissionIds, String menuIds) {
        Role role = roleDao.save(model);
        if(permissionIds!=null&& !permissionIds.equals("")) {
            String[] permissions = permissionIds.split(",");
            Set<Permission> permissionSet = role.getPermissions();
            for (int i = 0; i < permissions.length; i++) {
                Integer j = Integer.parseInt(permissions[i]);
                Permission one = permissionDao.findOne(j);
                permissionSet.add(one);
            }
        }
        if(menuIds!=null&&!menuIds.equals("")) {
            String[] menus = menuIds.split(",");
            Set<Menu> menuSet = role.getMenus();
            for (int i = 0; i < menus.length; i++) {
                Integer j = Integer.parseInt(menus[i]);
                Menu one = menuDao.findOne(j);
                menuSet.add(one);
            }
        }

    }

}
