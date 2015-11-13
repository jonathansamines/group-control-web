package com.jonathansamines.dao.models;

import java.util.ArrayList;

/**
 * Group of Permission model
 * @author jonathansamines
 */
public class Group {
    
    private int groupId;
    private String name;
    private ArrayList<Permission> permissions;
    
    public Group(String name) {
        this.name = name;
        this.permissions = new ArrayList<>();
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Permission> getPermissions() {
        return permissions;
    }

    public void addPermission(Permission permission) {
        this.permissions.add(permission);
    }
}
