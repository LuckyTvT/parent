package com.itheima.realm;

import com.itheima.fore_service.server.CrmService;
import com.itheima.fore_service.server.Customer;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

public class ForeRealm extends AuthorizingRealm {

    @Autowired
    private CrmService crmService;

//    授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

//    认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        Customer customer = crmService.login(username, password);
        if(customer!=null){
//            这个simpleAuthenticationInfo是AuthenticationInfo的实现类
            return new SimpleAuthenticationInfo(customer, password, getName());
        }else{
            return null;
        }

    }
}
