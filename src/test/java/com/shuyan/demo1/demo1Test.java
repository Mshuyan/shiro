package com.shuyan.demo1;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.junit.Assert;
import org.junit.Test;


public class demo1Test {
    @Test
    public void testHelloWorld(){
        //1、获取 SecurityManager 工厂，此处使用 Ini 配置文件初始化SecurityManager,
        //  shiro.ini中存储着所有用户正确的账号密码，相当于数据库的user表
        Factory<SecurityManager> factory = new IniSecurityManagerFactory("classpath:shiro.ini");
        //2、得到 SecurityManager 实例 并绑定给 SecurityUtils
        SecurityManager securityManager = factory.getInstance();
        SecurityUtils.setSecurityManager(securityManager);
        // 以上是全局配置，执行1次即可

        //3、得到 Subject 对象，该对象会自动绑定到当前线程，web程序中，请求结束时需要手动解除绑定
        Subject subject = SecurityUtils.getSubject();
        //  创建1个 UsernamePasswordToken 对象，里面是1个用户的验证凭证
        UsernamePasswordToken token = new UsernamePasswordToken("zhang", "123");
        try {
            //4、登录，即身份验证
            subject.login(token);
        } catch (AuthenticationException e) {
            /*
             * AuthenticationException 是1个笼统的身份验证的异常，他还有很多子类；
             *      DisabledAccountException(禁用的帐号)、
             *      LockedAccountException(锁定的帐号)、
             *      UnknownAccountException(错误的帐号)、
             *      ExcessiveAttemptsException(登录失败次数过 多)、
             *      IncorrectCredentialsException (错误的凭证)、
             *      ExpiredCredentialsException(过期的 凭证)
             *      等。。。
             */
            //5、身份验证失败
            System.out.println("登录失败");
            return;
        }
        System.out.println("登录成功");
        //6、退出登录
        subject.logout();
    }
}
