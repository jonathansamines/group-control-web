package com.jonathansamines.models;

/**
 * Permission detail model
 * @author jonathansamines
 */
public class Permission {
    
    private int permissionId;
    private String name;
    
    public Permission(String name) {
        this.name = name;
    }

    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }    
}
