package com.itheima.action;


import com.itheima.fore_service.server.CrmService;
import com.itheima.fore_service.server.Customer;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

public class RegistAction extends BaseAction<Customer>{

    @Autowired
    CrmService crmService;


    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    private String checkcode;

    @Action("regist")
    public void regist(){
//        先判断传递过来的checkcode是否与session中保存的随机生成的验证码一致
        String code = (String) ServletActionContext.getRequest().getSession().getAttribute("telephone");
        System.out.println("code:"+code);
        System.out.println("checkcode:"+checkcode);
        HashMap<String, Object> map = new HashMap<>();
        if(code.equals(checkcode)){
            Customer customer = crmService.save(model);
            if(customer!=null){
                map.put("success",true);
                crmService.sendEmail(customer);
                toJSON(map);
            }else{
                map.put("success",false);
                map.put("message","激活邮件发送失败");
                toJSON(map);
            }
        }else{
            map.put("success",false);
            map.put("message","注册失败");
            toJSON(map);
        }
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    private String uuid;


    @Action("active")
    public void active() {
        boolean flag = crmService.active(model.getTelephone(),uuid);
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        if(flag) {
            try {
                response.sendRedirect(request.getContextPath() + "/registSuccess.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            try {
                response.sendRedirect(request.getContextPath() + "/registerFailed.html");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
