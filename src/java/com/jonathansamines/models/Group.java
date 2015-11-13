package com.jonathansamines.models;

/**
 * Group of Permission model
 * @author jonathansamines
 */
public class Group {
    
    private int groupId;
    private String name;
    
    public Group(String name) {
        this.name = name;
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
}
