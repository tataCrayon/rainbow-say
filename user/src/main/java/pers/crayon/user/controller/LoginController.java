package pers.crayon.user.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import pers.crayon.user.model.pojo.User;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/8 14:50
 * @since JDK 1.8
 */
@Slf4j
@RestController
@Api(tags = "LoginController", description = "登录权限限制，编写一个简单的登录方法，一个index页的查询方法，一个add方法，一个admin方法，对应不同的角色或权限拦截")
public class LoginController {

    @GetMapping("/login")
    @ApiOperation(value = "用户登录接口")
    public String login(User user) {
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            return "请输入用户名和密码！";
        }
        //用户认证信息
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(
                user.getUserName(),
                user.getPassword()
        );
        try {
            //进行验证，这里可以捕获异常，然后返回对应信息
            subject.login(usernamePasswordToken);
//            subject.checkRole("admin");
//            subject.checkPermissions("query", "add");
        } catch (UnknownAccountException e) {
            log.error("用户名不存在！", e);
            return "用户名不存在！";
        } catch (AuthenticationException e) {
            log.error("账号或密码错误！", e);
            return "账号或密码错误！";
        } catch (AuthorizationException e) {
            log.error("没有权限！", e);
            return "没有权限";
        }
        return "login success";
    }

    @RequiresRoles("admin")
    @GetMapping("/admin")
    public String admin() {
        return "admin success!";
    }

    @RequiresPermissions("query")
    @GetMapping("/index")
    public String index() {
        return "index success!";
    }

    @RequiresPermissions("add")
    @GetMapping("/add")
    public String add() {
        return "add success!";
    }
}



/*
1. AuthenticationException 认证异常
Shiro在登录认证过程中，认证失败需要抛出的异常。 AuthenticationException包含以下子类：

1.1. CredentitalsException 凭证异常
IncorrectCredentialsException 不正确的凭证
ExpiredCredentialsException 凭证过期

1.2. AccountException 账号异常
ConcurrentAccessException: 并发访问异常（多个用户同时登录时抛出）
UnknownAccountException: 未知的账号
ExcessiveAttemptsException: 认证次数超过限制
DisabledAccountException: 禁用的账号
LockedAccountException: 账号被锁定
UnsupportedTokenException: 使用了不支持的Token

2. AuthorizationException: 授权异常
Shiro在登录认证过程中，授权失败需要抛出的异常。 AuthorizationException包含以下子类：

2.1. UnauthorizedException:
抛出以指示请求的操作或对请求的资源的访问是不允许的。

2.2. UnanthenticatedException:
当尚未完成成功认证时，尝试执行授权操作时引发异常。

作者：王诗林
链接：https://www.jianshu.com/p/7f724bec3dc3
来源：简书
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */