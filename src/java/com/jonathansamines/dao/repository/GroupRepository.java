package com.jonathansamines.dao.repository;

import com.jonathansamines.dao.ConnectionManager;
import com.jonathansamines.dao.models.Group;
import com.jonathansamines.dao.models.Permission;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * GroupRepository, map the operations of the Group Entity
 * @author jonathansamines
 */
public class GroupRepository implements IRepository<Group> {

    @Override
    public ArrayList get() {
        ArrayList<String> names = new ArrayList<>();
        ArrayList<Group> groups = new ArrayList<>();

        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            ResultSet set = st.executeQuery("SELECT groups.name as group_name, groups.id_group, permissions.name as permission_name, permissions.id_permission as id_permission, permissions.path as permission_path, permissions.display as permission_display FROM groups LEFT JOIN group_permissions ON groups.id_group = group_permissions.id_group LEFT JOIN permissions ON group_permissions.id_permission = permissions.id_permission;");
            Group group = null;

            while(set.next()) {
                group = new Group(set.getString("group_name"));
                group.setGroupId(set.getInt("id_group"));
                
                int index = names.indexOf(group.getName());

                if (index == -1) {
                    groups.add(group);
                    names.add(group.getName());
                }else {
                    group = groups.get(index);
                }
                
                Permission permission = new Permission(set.getString("permission_name"), null, null);
                permission.setPermissionId(set.getInt("id_permission"));
                
                group.addPermission(permission);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return groups;
    }

    @Override
    public Group getById(int idGroup) {
        Group group = null;

        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            ResultSet set = st.executeQuery("SELECT groups.name as group_name, groups.id_group, permissions.name as permission_name, permissions.id_permission as id_permission, permissions.path as permission_path, permissions.display as permission_display FROM groups LEFT JOIN group_permissions ON groups.id_group = group_permissions.id_group LEFT JOIN permissions ON group_permissions.id_permission = permissions.id_permission WHERE groups.id_group = " + idGroup + ";");

            while(set.next()) {
                if (group == null) {
                    group = new Group(set.getString("group_name"));
                    group.setGroupId(set.getInt("id_group"));
                }

                Permission permission = new Permission(set.getString("permission_name"), null, null);
                permission.setPermissionId(set.getInt("id_permission"));
                
                group.addPermission(permission);
            }
            
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return group;
    }

    @Override
    public boolean create(Group group) {
        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            st.execute("INSERT INTO groups(name) VALUES('" + group.getName() + "')");
            
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean update(Group group) {
        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            st.execute("UPDATE groups SET name = '" + group.getName() + "' WHERE id_group = " + group.getGroupId() + ";");
            
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
    
    public boolean delete(int groupId) {
        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            st.execute("DELETE FROM groups WHERE id_group = " + groupId + ";");
            
            return true;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
