package pers.crayon.user.shiro;

import io.swagger.annotations.Api;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import pers.crayon.user.model.dto.PermissionsDto;
import pers.crayon.user.model.dto.RoleDto;
import pers.crayon.user.model.dto.UserDto;
import pers.crayon.user.service.LoginService;

/**
 * @author tataCrayon
 * @version 1.0
 * @date 2020/12/8 11:46
 * @since JDK 1.8
 */
@Api(tags = "CustomRealm", description = "自定义Realm用于查询用户的角色和权限信息并保存到权限管理器")
public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    /**
     * @MethodName doGetAuthorizationInfo
     * @Description 权限配置类==授权
     * @Param [principalCollection]
     * @Return AuthorizationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取登录用户名
        String name = (String) principalCollection.getPrimaryPrincipal();
        //查询用户名称对应角色、权限
        UserDto user = loginService.getUserByName(name);
        //添加角色和权限
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        for (RoleDto role : user.getRoles()) {
            //添加角色
            simpleAuthorizationInfo.addRole(role.getRoleName());
            //添加权限，可以直接添加，此处是给角色和权限设置模型
            //simpleAuthorizationInfo.addStringPermission("admin");
            for (PermissionsDto permissions : role.getPermissions()) {
                simpleAuthorizationInfo.addStringPermission(permissions.getPermissionsName());
            }
        }
        // 用户登录——认证，认证侧存，可以在授权这边拿到用户身份信息,然后可以动态设置权限
        //Subject subject = SecurityUtils.getSubject();
        //User currentUser = (User) subject.getPrincipal();
        //simpleAuthorizationInfo.addStringPermission(currentUser.getPermission);

        return simpleAuthorizationInfo;
    }

    /**
     * @MethodName doGetAuthenticationInfo
     * @Description 认证配置类==认证
     * @Param [authenticationToken]
     * @Return AuthenticationInfo
     * @Author WangShiLin
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {

        if (StringUtils.isEmpty(authenticationToken.getPrincipal())) {
            return null;
        }
        // 向下类型转换,转换token
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String userName = token.getUsername();
        UserDto dbUser = loginService.getUserByName(userName);

        //获取用户信息
        String name = authenticationToken.getPrincipal().toString();
        UserDto user = loginService.getUserByName(name);

        //String name = "";
        if (dbUser == null) {
            // 抛出 UnknownAccountException
            return null;
        } else {
            //这里验证authenticationToken和simpleAuthenticationInfo的信息,密码认证
            SimpleAuthenticationInfo simpleAuthenticationInfo =
                    new SimpleAuthenticationInfo(dbUser, dbUser.getPassword(), getName());
            //new SimpleAuthenticationInfo(name, user.getPassword(), getName());
            // 参数为获取 principal,credentials,realmName
            return simpleAuthenticationInfo;
        }
    }
}
