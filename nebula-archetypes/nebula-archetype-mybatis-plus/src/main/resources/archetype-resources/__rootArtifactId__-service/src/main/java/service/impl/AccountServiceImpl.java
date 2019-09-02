package ${package}.service.impl;

import com.google.common.collect.Sets;
import io.nebula.kernel.exception.LoginException;
import io.nebula.kernel.security.IUser;
import io.nebula.util.StringUtil;
import ${package}.dto.User;
import ${package}.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * 登录、角色设定、权限设定
 */
@Slf4j
@Component
public class AccountServiceImpl implements AccountService {

    @Override
    public Set<String> listRole(String username) {
        Set<String> roleSet = Sets.newHashSet();
        roleSet.add("user");
        return roleSet;
    }

    @Override
    public Set<String> listPermission(String s) {
        Set<String> roleSet = Sets.newHashSet();
        roleSet.add("plan:create");
        return roleSet;
    }

    @Override
    public IUser findByName(String username) {
        User user = new User();
        user.setUsername("18888888888");
        user.setNickName("管理员");
        return user;
    }

    @Override
    public IUser validate(String username, String password, Map<String, Object> map) throws LoginException {
        User user;
        if (!StringUtil.isEmpty(username) && !StringUtil.isEmpty(password)) {
            if (password.equals("666666")) {
                user = (User) findByName(username);
            } else {
                throw new LoginException(LoginException.Reason.BadCredential);
            }
        } else {
            throw new LoginException(LoginException.Reason.NotFound);
        }
        return user;
    }

}
