package io.nebula.web.shiro;

import com.google.common.collect.Maps;
import io.nebula.kernel.Constants;
import io.nebula.kernel.exception.BusinessException;
import io.nebula.kernel.exception.LoginException;
import io.nebula.kernel.security.IUser;
import io.nebula.kernel.security.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Map;

/**
 * @author 徐步龙
 * @version V1.0
 * @date 2018/12/18
 */
public class NebulaRealm extends AuthorizingRealm {
    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        if (null == userService) {
            throw new BusinessException("没有找到 UserService 的业务实现，nebula 无法提供用户验证相关功能。");
        }
        String principal = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.setStringPermissions(userService.listPermission(principal));
        info.setRoles(userService.listRole(principal));
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        if (null == userService) {
            throw new BusinessException("没有找到 UserService 的业务实现，nebula 无法提供用户验证相关功能。");
        }
        try {
            String userName = (String) token.getPrincipal();
            String password = new String((char[]) token.getCredentials());
            Map<String, Object> properties = Maps.newHashMap();
            IUser user = userService.validate(userName, password, properties);
            SecurityUtils.getSubject().getSession().setAttribute(Constants.SESSION_USER, user);
            return new SimpleAuthenticationInfo(userName, password, getName());
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
