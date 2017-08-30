package com.itheima.bos.action;

import com.itheima.bos.domain.SubArea;
import com.itheima.bos.service.SubAreaService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class SubAreaAction extends BaseAction<SubArea> {

    @Autowired
    SubAreaService subAreaService;

    @Action("findSubAreaByPage")
    public void findSubAreaByPage(){
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<SubArea> byPage = subAreaService.findSubAreaByPage(pageable);
        javaToJSON(byPage,"subareas","couriers");
    }

    @Action("subArea_save")
    public void subArea_save() {

        subAreaService.save(model);
    }

    @Action("findByFixedAreaIsNull")
    public void findByFixedAreaIsNull(){
        List<SubArea> list = subAreaService.findByFixedAreaIsNull();
        toJSON(list,"subareas","couriers");
    }


}