package com.itheima.bos.realm;

import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.LoginService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BosRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权操作");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("courier");
        List<String> list = new ArrayList<>();
        list.add("courier");
        list.add("del");
        info.addStringPermissions(list);
        return info;
    }

//    认证   相当于登录校验
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("认证操作");
//        查询数据库
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        User user = loginService.login(username, password);
        if(user==null){
            return null;
        }else{
//            principal：主角   credentials：密码   realmName：realm的名字(getName())
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,password,getName());
            return info;
        }
//        完成该步骤后 将realm交给spring 管理  配置applicationContext.xml  并注入到安全管理器中
    }
}
