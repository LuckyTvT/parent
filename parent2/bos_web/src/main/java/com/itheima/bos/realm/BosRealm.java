package com.itheima.bos.realm;

import com.itheima.bos.domain.system.Permission;
import com.itheima.bos.domain.system.Role;
import com.itheima.bos.domain.system.User;
import com.itheima.bos.service.LoginService;
import com.itheima.bos.service.PermissionService;
import com.sun.org.apache.bcel.internal.generic.ARRAYLENGTH;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class BosRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;
    @Autowired
    private PermissionService permissionService;

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("授权操作");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        获取当前登录用户对象
//        User user = (User) SecurityUtils.getSubject().getPrincipal();

//        获取当前登录人
        User user = (User) principalCollection.getPrimaryPrincipal();
//        去数据库查询用户权限
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            info.addRole(role.getKeyword());
            System.out.println(1);
        }

        if (roles != null && !roles.equals("")) {
            for (Role role : roles) {
                List<Permission> permission = permissionService.findByRole(role);
                for (Permission permission1 : permission) {
                    info.addStringPermission(permission1.getKeyword());
                    System.out.println(2);
                }
            }
        }
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
