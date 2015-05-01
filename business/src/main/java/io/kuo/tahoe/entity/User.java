package io.kuo.tahoe.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nikog on 2/4/2015.
 */
@Entity
@Table(name = "sys_user")
public class User extends IdEntity {
    private String email;
    private String plainPassword;
    private String password;
    private String salt;
    private String nickName;
    private String status;

    private List<Role> roles = Lists.newArrayList();

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Transient
    @JsonIgnore
    public String getPlainPassword() {
        return plainPassword;
    }

    public void setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
    }

    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @ManyToMany
    @JoinTable(name = "sys_user_role", joinColumns = {@JoinColumn(name = "user_id")}, inverseJoinColumns = {@JoinColumn(name = "role_id")})
    @Fetch(FetchMode.JOIN)
    @OrderBy("id ASC")
    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roleList) {
        this.roles = roleList;
    }

    @Override
    public String toString() {
        return String.format("User[id=%d, email='%s', nickName='%s']", id, email, nickName);
    }
}
