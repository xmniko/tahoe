/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package io.kuo.tahoe.entity;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "sys_role")
public class Role extends IdEntity {

    private String name;

    private String permissions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }

    @Transient
    public List<String> getPermissionList() {
        return ImmutableList.copyOf(StringUtils.split(permissions, ","));
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
