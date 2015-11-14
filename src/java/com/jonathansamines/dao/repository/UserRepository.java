package com.jonathansamines.dao.repository;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.jonathansamines.dao.models.User;
import com.jonathansamines.dao.ConnectionManager;
import com.jonathansamines.dao.models.Group;
import com.jonathansamines.dao.models.Permission;

/**
 * UserRepository, map operations to the internal User entity
 * @author jonathansamines
 */
public class UserRepository implements IRepository<User> {
    
    @Override
    public ArrayList<User> get() {
        ArrayList<User> users = new ArrayList<>();

        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            ResultSet set = st.executeQuery("SELECT * FROM users LEFT JOIN groups ON groups.id_group = users.id_group;");

            while(set.next()) {
                User user = new User(set.getString("username"), set.getString("firstname"), set.getString("lastname"));
                user.setUserId(set.getInt("id_user"));

                Group group = new Group(set.getString("name"));
                group.setGroupId(set.getInt("id_group"));
                
                user.setGroup(group);
                
                users.add(user);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return users;
    }
    
    public User validateCredentials(String username, String password) {
        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            ResultSet set = st.executeQuery("SELECT id_user, username, firstname, lastname, groups.name as group_name, groups.id_group, permissions.name as permission_name, permissions.id_permission as id_permission, permissions.path as permission_path, permissions.display as permission_display FROM users LEFT JOIN groups ON groups.id_group = users.id_group LEFT JOIN group_permissions ON groups.id_group = group_permissions.id_group LEFT JOIN permissions ON group_permissions.id_permission = permissions.id_permission WHERE username = '" + username + "' AND password = MD5('" + password + "');");
            User user = null;

            while(set.next()) {
                if (user == null) {
                    user = new User(set.getString("username"), set.getString("firstname"), set.getString("lastname"));
                    user.setUserId(set.getInt("id_user"));
                    
                    Group group = new Group(set.getString("group_name"));
                    group.setGroupId(set.getInt("id_group"));
                    user.setGroup(group);
                }
                
                Permission permission = new Permission(set.getString("permission_name"), set.getString("permission_path"), set.getString("permission_display"));
                permission.setPermissionId(set.getInt("id_permission"));
                
                user.getGroup().addPermission(permission);
            }
            
            return user;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public User getById(int userId) {
        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            ResultSet set = st.executeQuery("SELECT id_user, username, firstname, lastname, groups.name as group_name, groups.id_group, permissions.name as permission_name, permissions.id_permission as id_permission, permissions.path as permission_path, permissions.display as permission_display FROM users LEFT JOIN groups ON groups.id_group = users.id_group LEFT JOIN group_permissions ON groups.id_group = group_permissions.id_group LEFT JOIN permissions ON group_permissions.id_permission = permissions.id_permission WHERE id_user = " + userId + ";");
            User user = null;

            while(set.next()) {
                if (user == null) {
                    user = new User(set.getString("username"), set.getString("firstname"), set.getString("lastname"));
                    user.setUserId(set.getInt("id_user"));
                    
                    Group group = new Group(set.getString("group_name"));
                    group.setGroupId(set.getInt("id_group"));
                    user.setGroup(group);
                }
                
                Permission permission = new Permission(set.getString("permission_name"), set.getString("permission_path"), set.getString("permission_display"));
                permission.setPermissionId(set.getInt("id_permission"));
                
                user.getGroup().addPermission(permission);
            }
            
            return user;
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public boolean create(User user) {
        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            
            return st.execute("INSERT INTO users (username, firstname, lastname, id_group, password) VALUES('" + user.getUsername() + "', '" + user.getFirstName() + "', '" + user.getLastName() + "', " + user.getGroup().getGroupId() + ", MD5('" + user.getPassword() + "'));");
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean update(User user) {
        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            
            return st.execute("UPDATE users SET username = '" + user.getUsername() +  "', firstname = '" + user.getFirstName() + "', lastname = '" + user.getLastName() + "', id_group = " + user.getGroup().getGroupId() + " WHERE id_user = " + user.getUserId() + ";");
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
