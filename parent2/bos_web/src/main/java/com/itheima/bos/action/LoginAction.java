package com.itheima.bos.action;

import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.LoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;


public class LoginAction extends BaseAction<User> {

    @Autowired
    private LoginService loginService;

    private String checkCode;

    public void setCheckCode(String checkCode) {
        this.checkCode = checkCode;
    }

    @Action("bos_login")
    public void bos_login(){
        String code = (String) ServletActionContext.getRequest().getSession().getAttribute("validateCode");
        HashMap<String, Object> map = new HashMap<>();
        System.out.println("code:"+code+"   checkCode:"+checkCode);
        if(!code.equalsIgnoreCase(checkCode)){
//            返回验证码错误提示
            map.put("success",false);
            map.put("message","验证码错误");
            toJSON(map);
        }else{
//            User user = loginService.login(model.getUsername()model.getPassword());
//            if(user!=null){
//                HttpServletRequest request = ServletActionContext.getRequest();
//                HttpServletResponse response = ServletActionContext.getResponse();
//                request.getSession().setAttribute("user",user);
//                map.put("success",true);
//                toJSON(map);
//            }else{
////                返回用户名密码错误
//                map.put("success",false);
//                map.put("message","用户名或密码错误");
//                toJSON(map);
//            }

//------------------------------shiro框架进行登录校验------------------------
//            获取令牌
            UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(), model.getPassword());
//            获取主题
            Subject subject = SecurityUtils.getSubject();
//            开始认证  这里login需要的是AuthenticationToken 而上面的UsernamePasswordToken是它的实现类，所以可以传入token
//            这里需要用try  catch来捕获AuthenticationException 进行流程控制。
            try{
                subject.login(token);
                map.put("success",true);
                toJSON(map);
            }catch (AuthenticationException e){
                map.put("success",false);
                map.put("message","账号或密码错误");
                toJSON(map);
            }


        }
    }

    @Action("bos_logout")
    public void bos_logout(){
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.getSession().removeAttribute("user");
//        try {
//            response.sendRedirect(request.getContextPath()+"login.html");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        SecurityUtils.getSubject().logout();
        HttpServletResponse response = ServletActionContext.getResponse();
        HttpServletRequest request = ServletActionContext.getRequest();
        try {
            response.sendRedirect(request.getContextPath()+"login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Action("getUsername")
    public void getUsername() {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        User user = (User) request.getSession().getAttribute("user");
//        if(user==null) {
//            map.put("success",false);
//            toJSON(map);
//        }else {
//            map.put("success",true);
//            map.put("message",user.getUsername());
//            toJSON(map);
//        }

//        -----------------使用shiro来获取用户名----------------
        HashMap<String, Object> map = new HashMap<>();
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        if(user==null){
            map.put("success",false);
            toJSON(map);
        }else{
            map.put("success",true);
            map.put("message",user.getUsername());
            toJSON(map);
        }

    }



}
