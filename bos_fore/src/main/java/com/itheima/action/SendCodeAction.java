package com.itheima.action;

import com.itheima.fore_service.server.CrmService;
import com.itheima.fore_service.server.Customer;
import com.itheima.utils.SmsUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;


public class SendCodeAction extends BaseAction<Customer>{

    @Autowired
    CrmService crmService;



    @Action("sendCode")
    public void sendCode(){
        String s = RandomStringUtils.randomNumeric(4);
        SmsUtils.sendSmsByWebService(model.getTelephone(),"您的验证码为:"+s);
        System.out.println("验证码："+s);
        HttpSession session = ServletActionContext.getRequest().getSession();
        session.setAttribute("telephone",s);
    }

    @Action("findByTelephoneAndUsername")
    public void findByTelephoneAndUsername(){
        Customer c1 = crmService.findByTelephone(model.getTelephone());
        Customer c2 = crmService.findByUsername(model.getUsername());
        HashMap<String, Object> map = new HashMap<>();
        if(c1!=null){
            map.put("success",false);
            map.put("message","该号码已被注册");
            toJSON(map);
        }else if(c2!=null){
            map.put("success",false);
            map.put("message","该用户名已存在");
            toJSON(map);
        }else {
            map.put("success",true);
            toJSON(map);
        }
    }


}
