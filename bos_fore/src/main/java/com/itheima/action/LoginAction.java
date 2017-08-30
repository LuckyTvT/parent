package com.itheima.action;

import com.itheima.fore_service.server.CrmService;
import com.itheima.fore_service.server.Customer;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class LoginAction extends BaseAction<Customer>{

    @Autowired
    private CrmService crmService;

    public void setCheckcode(String checkcode) {
        this.checkcode = checkcode;
    }
    private String checkcode;

    public void setUser(String user) {
        this.user = user;
    }
    private String user;

    @Action("login")
    public void login(){
        HttpServletRequest request = ServletActionContext.getRequest();
        String validateCode = (String) request.getSession().getAttribute("validateCode");
        HttpServletResponse response = ServletActionContext.getResponse();
        HashMap<String, Object> map = new HashMap<>();
        if(user.equals("")){
            map.put("success",false);
            map.put("message","用户名不能为空");
            toJSON(map);
        }else if(model.getPassword().equals("")){
            map.put("success",false);
            map.put("message","密码不能为空");
            toJSON(map);
        }else if (checkcode.equalsIgnoreCase(validateCode)){
//            获取令牌
            UsernamePasswordToken token = new UsernamePasswordToken();
//            获取主题
            Subject subject = SecurityUtils.getSubject();
//            开始认证 通过捕获AuthenticationException 来进行流程控制  登陆成功或失败
            try{
                subject.login(token);
//                执行login方法会跳转到自己写的realm类，执行其中的doGetAuthenticationInfo方法。
                map.put("success",true);
                toJSON(map);
            }catch (AuthenticationException e){
                map.put("success",false);
                map.put("message","用户名或密码不正确");
                toJSON(map);
            }

        }else{
            map.put("success",false);
            map.put("message","验证码错误");
            toJSON(map);
        }
    }

}
