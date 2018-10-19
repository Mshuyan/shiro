> 学习资料：<br/>
    1. 工程根目录下的`跟我学Shiro教程.pdf`

# 1. Realm
## 1.1. 内置Realm
+ 内置Realm关系图
    ```java
                                            Realm
                                              |
                                         CachingRealm
                                              |
                                       AuthenticatingRealm
                                              |
                                        AuthorizingRealm
                       __________________|   |   |   |________________
                      |                  ____|   |____                |
                      |                 |             |               |
             SimpleAccountRealm     JdbcRealm    JndiLdapRealm  AbstractLdapRealm
                      |                                               |
           TextConfigurationRealm                              ActiveDirectoryRealm
               |            |
       PropertiesRealm   IniRealm
    ```
+ `IniRealm`<br/>
    + `[users]`<br/>
        该部分指定用户名、密码、及其角色
    + `[roles]`<br/>
        该部分指定角色及其权限
+ `PropertiesRealm`<br/>
    user.username=password,role1,role2 指定用户名/密码及其角色;<br/>
    role.role1=permission1,permission2 指定角色及权限信息;
+ `JdbcRealm`<br/>
    通过sql语句查询相应的信息
    + `ini`配置文件内容参见`shiro-jdbc-realm.ini`
    + java代码与前面的demo中相同
    + 默认情况下，jdbcRealm支持1套对默认的表结构的查询操作，但是项目中表结构不可能按照默认的方式进行设计，所以常规做法应该是自己实现通过sql语句进行验证
    
## 1.2. 自定义Reaml
> 自定义Realm通过继承`AuthorizingRealm`实现即可
# 2. `Authenticator`及`AuthenticationStrategy`接口
## 2.1. `Authenticator`接口
> 该接口是身份认证的入口，该接口只有1个方法

```java
public AuthenticationInfo authenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException;
```
## 2.2. `AuthenticationStrategy`接口
> `ModularRealmAuthenticator`委托给多个Realm认证时，验证规则通过`AuthenticationStrategy`接口指定<br/>
+ `AuthenticationStrategy`的默认实现类
    + `FirstSuccessfulStrategy`:<br/>
        只要有一个 Realm 验证成功即可，只返回第一个 Realm 身份验证 成功的认证信息，其他的忽略;
    + `AtLeastOneSuccessfulStrategy`:<br/>
        只要有一个 Realm 验证成功即可，和 FirstSuccessfulStrategy 不同，返回所有 Realm 身份验证成功的认证信息;
    + `AllSuccessfulStrategy`:<br/>
        所有 Realm 验证成功才算成功，且返回所有 Realm 身份验证成功的 认证信息，如果有一个失败就失败了。
+ `ModularRealmAuthenticator` 默认使用`AtLeastOneSuccessfulStrategy`,该属性在`ini`文件中指定
+ 自定义`Strategy`通过继承`org.apache.shiro.authc.pam.AbstractAuthenticationStrategy`实现即可
# 授权
## 


