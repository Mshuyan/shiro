package com.shuyan.demo3_strategy;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;


public class demo3Test {
    static {
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-strategy.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void testStrategy(){
        try{
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
            subject.login(token);
        }catch (Exception e){
            System.out.println("登录失败");
            return;
        }
        /*
         * 第1次获取subject对象时，该对象已经绑定到当前线程，
         * 所以在该线程内再次调用 getSubject 方法时，获取的仍然是上次获取的 subject对象
         */
        Subject subject = SecurityUtils.getSubject();
        //得到当前线程绑定的 subject 对象验证的用户的身份集合，其包含了 Realm 验证成功的身份信息
        PrincipalCollection principalCollection = subject.getPrincipals();
        System.out.println(principalCollection);
    }
}
