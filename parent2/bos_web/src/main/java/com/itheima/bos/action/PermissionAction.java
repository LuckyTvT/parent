package com.itheima.bos.action;

import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.service.PermissionService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

public class PermissionAction extends BaseAction<Permission> {

    @Autowired
    PermissionService permissionService;


    @Action("permission_findAll")
    public void permission_findAll(){
        List<Permission> list =  permissionService.findAll();
        toJSON(list,"roles");
    }

    @Action("permission_save")
    public void permission_save(){
        Permission permission = permissionService.save(model);
        HashMap<String, Object> map = new HashMap<>();
        if(permission==null){
            map.put("success",false);
            map.put("message","保存失败");
            toJSON(map);
        }else{
            map.put("success",true);
            map.put("message","保存成功");
            toJSON(map);
        }
    }


}
