package com.itheima.bos.action;

import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.UserService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class UserAction extends BaseAction<User>{

    @Autowired
    UserService userService;

    private String roleIds;

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    @Action("user_save")
    public void user_save(){
        userService.save(roleIds,model);
    }

    @Action("user_findAll")
    public void user_findAll(){
        List<User> list = userService.findAll();
        toJSON(list,"roles");
    }


}
