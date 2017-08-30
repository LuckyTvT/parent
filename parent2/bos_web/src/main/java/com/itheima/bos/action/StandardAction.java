package com.itheima.bos.action;

import com.alibaba.fastjson.JSON;
import com.itheima.bos.domain.Standard;
import com.itheima.bos.service.BosService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;


public class StandardAction extends BaseAction<Standard> {

    @Autowired
    BosService service;


    HttpServletResponse response = ServletActionContext.getResponse();

    @Action(value = "StandardSave")
    public String StandardSave(){
        service.StandardSave(model);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success","成功");
        map.put("message","存储成功");
        String json = JSON.toJSONString(map);
        try {
            response.getWriter().write(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

//  使用属性驱动获取当前页数page和每页显示信息条数rows
    @Action(value = "findStandardByPage")
    public void findStandardByPage() {
//      该方法中的page是以零开始
        Pageable pageable = new PageRequest(page-1,rows);
        Page page = service.findStandardByPage(pageable);
        javaToJSON(page);
    }

    public void setQ(String q) {
        this.q = q;
    }
    private String q;

    @Action("findStandardAll")
    public void findStandardAll() {
        if(q==null){
            List<Standard> list = service.findStandardAll();
            toJSON(list);
        }else{
            List<Standard> list = service.findByQ(q);
            toJSON(list);
        }
    }



}
