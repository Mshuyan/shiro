package com.shuyan.demo2_realm;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {

    @Override
    //返回此realm的name
    public String getName() {
        return "myRealm2";
    }

    @Override
    //返回是否支持传入的这种类型的 token
    public boolean supports(AuthenticationToken authenticationToken) {
        return authenticationToken instanceof UsernamePasswordToken;
    }

    @Override
    // 根据传入的 token 获取对应的认证信息
    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal(); //得到用户名
        String password = new String((char[])token.getCredentials()); //得到密码
        if(!"lisi".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"1234".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }
        //如果身份认证验证成功，返回一个 AuthenticationInfo 实现;
        return new SimpleAuthenticationInfo(username, password, getName());
    }
}
