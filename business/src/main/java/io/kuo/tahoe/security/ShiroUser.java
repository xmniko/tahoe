package io.kuo.tahoe.security;

import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class ShiroUser implements Serializable {
    private static final long serialVersionUID = -1373760761780840081L;
    public String email;
    public String nickName;

    public ShiroUser(String email, String nickName) {
        this.email = email;
        this.nickName = nickName;
    }

    public String getNickName() {
        return nickName;
    }

    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return email;
    }

    /**
     * 重载hashCode,只计算loginName;
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(email);
    }

    /**
     * 重载equals,只计算loginName;
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ShiroUser other = (ShiroUser) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        return true;
    }
}
