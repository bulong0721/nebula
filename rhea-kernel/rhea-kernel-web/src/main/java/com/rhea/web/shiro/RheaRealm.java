package com.rhea.web.shiro;

import com.google.common.collect.Maps;
import com.rhea.kernel.exception.LoginException;
import com.rhea.kernel.security.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author xubulong
 * @version V1.0
 */
public class RheaRealm extends AuthorizingRealm {
    private UserService userService;
    private MenuService menuService;
    private RoleService roleService;

    public RheaRealm() {
    }

    public RheaRealm(UserService userService, RoleService roleService, MenuService menuService) {
        this.userService = userService;
        this.menuService = menuService;
        this.roleService = roleService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        List<IRole> roleList = roleService.listRole(principal);

        Set<String> roleSet = roleList.stream().map(IRole::getName).collect(Collectors.toSet());
        info.setRoles(roleSet);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        try {
            String userName = (String) token.getPrincipal();
            String password = new String((char[]) token.getCredentials());
            Map<String, Object> properties = Maps.newHashMap();
            IUser user = userService.validate(userName, password, properties);
            return new SimpleAuthenticationInfo(userName, password, user.getNickname());
        } catch (LoginException ae) {
            switch (ae.getReason()) {
                case NotFound:
                    throw new UnknownAccountException("找不到用户！");
                case Locked:
                    throw new LockedAccountException("账号已被锁定,请联系管理员！");
                case Expired:
                    throw new LockedAccountException("账号已过期,请联系管理员！");
                case BadCredential:
                    throw new IncorrectCredentialsException("用户名或密码错误！");
                default:
                    throw new AccountException("未知账号错误");
            }
        } catch (Exception ex) {
            throw new AccountException("未知账号错误");
        }
    }

    public void clearCache() {
        PrincipalCollection principals = SecurityUtils.getSubject().getPrincipals();
        super.clearCache(principals);
    }
}
