package com.jonathansamines.dao.models;

import java.util.ArrayList;

/**
 * Permission detail model
 * @author jonathansamines
 */
public class Permission {
    
    private int permissionId;
    private String name;
    private ArrayList<Group> groups;
    
    public Permission(String name) {
        this.name = name;
        this.groups = new ArrayList<>();
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

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }
}
