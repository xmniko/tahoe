package io.kuo.tahoe.security;

import io.kuo.tahoe.entity.Role;
import io.kuo.tahoe.entity.User;
import io.kuo.tahoe.service.UserService;
import io.kuo.utils.Encodes;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.PostConstruct;

/**
 * Created by nikog on 2/6/2015.
 */
public class ShiroDbRealm extends AuthorizingRealm {

    protected UserService userService;

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        User user = userService.findByEmail(token.getUsername());
        if (user != null) {
            if ("disabled".equals(user.getStatus())) {
                throw new DisabledAccountException();
            }

            byte[] salt = Encodes.decodeHex(user.getSalt());
            return new SimpleAuthenticationInfo(new ShiroUser(user.getEmail(), user.getNickName()), user.getPassword(),
                    ByteSource.Util.bytes(salt), getName());
        } else {
            return null;
        }
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
        User user = userService.findByEmail(shiroUser.email);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Role role : user.getRoles()) {
            // 基于Role的权限信息
            info.addRole(role.getName());
            // 基于Permission的权限信息
            info.addStringPermissions(role.getPermissionList());
        }
        return info;
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(EncryptUtil.HASH_ALGORITHM);
        matcher.setHashIterations(EncryptUtil.HASH_INTERATIONS);

        setCredentialsMatcher(matcher);
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

