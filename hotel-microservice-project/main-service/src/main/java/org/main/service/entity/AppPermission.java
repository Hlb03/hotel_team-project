package org.main.service.entity;

import java.io.Serializable;

public enum AppPermission implements Serializable {
    READ("get_content"),
    WRITE("create_content"),
    UPDATE("refresh_content"),
    DELETE("remove_content");


    private final String permissionDescription;

    AppPermission(String permissionDescription) {
        this.permissionDescription = permissionDescription;
    }

    public String getPermissionDescription() {
        return permissionDescription;
    }
}
