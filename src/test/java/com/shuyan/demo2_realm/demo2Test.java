package com.shuyan.demo2_realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Test;


public class demo2Test {
    @Test
    public void testReaml(){
        //在 shiro-realm.ini 中指定了使用哪些 realm
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
            return;
        }
        System.out.println("登录成功");
        subject.logout();
    }

    @Test
    public void testJdbcRealm(){
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken("zhang1", "123");
        try {
            subject.login(token);
        } catch (AuthenticationException e) {
            System.out.println("登录失败");
            return;
        }
        System.out.println("登录成功");
        subject.logout();
    }
}
