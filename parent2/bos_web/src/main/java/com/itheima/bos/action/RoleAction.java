package com.itheima.bos.action;

import com.itheima.bos.domain.system.Role;
import com.itheima.bos.service.RoleService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class RoleAction extends BaseAction<Role> {

    @Autowired
    RoleService roleService;


    @Action("role_findAll")
    public void role_findAll(){
        List<Role> list = roleService.findAll();
        toJSON(list,"users","permissions","menus");
    }

    private String permissionIds;
    private String menuIds;
    public void setPermissionIds(String permissionIds) {
        this.permissionIds = permissionIds;
    }
    public void setMenuIds(String menuIds) {
        this.menuIds = menuIds;
    }

    @Action("role_save")
    public void role_save() {
        roleService.save(model,permissionIds,menuIds);
    }

}
