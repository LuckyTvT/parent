package com.itheima.bos.action;

import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.service.MenuService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;


public class MenuAction extends BaseAction<Menu> {

    @Autowired
    MenuService menuService;

    @Action("menu_findByPidIsNull")
    public void menu_findByPidIsNull(){
        List<Menu> list = menuService.menu_findByPidIsNull();
        toJSON(list,"roles","childrenMenus","parentMenu");
    }

    @Action("menu_save")
    public void menu_save(){
        Menu menu = menuService.save(model);
        HashMap<String, Object> map = new HashMap<>();
        if(menu==null){
            map.put("success",false);
            map.put("message","保存过程中出错！");
            toJSON(map);
        }else{
            map.put("success",true);
            map.put("message","保存成功");
            toJSON(map);
        }
    }


}
