package com.shuyan.demo3_strategy;

import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

import java.util.ArrayList;
import java.util.List;

public class MyRealm1 implements Realm {

    @Override
    //返回此realm的name
    public String getName() {
        return "myRealm1";
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

        List<UsernamePasswordToken> list = new ArrayList<>();
        list.add(new UsernamePasswordToken("wang", "123"));
        list.add(new UsernamePasswordToken("zhang", "123"));

        for (UsernamePasswordToken tk: list) {
            if(tk.getPrincipal().equals(username)){
                if(new String((char[])tk.getCredentials()).equals(password)) {
                    return new SimpleAuthenticationInfo(username, password, getName());
                }
                throw new IncorrectCredentialsException(); //如果密码错误
            }
        }
        throw new UnknownAccountException(); //如果用户名错误
    }
}
