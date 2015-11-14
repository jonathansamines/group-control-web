package com.jonathansamines.dao.repository;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

import com.jonathansamines.dao.models.User;
import com.jonathansamines.dao.ConnectionManager;
import com.jonathansamines.dao.models.Group;

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
            ResultSet set = st.executeQuery("SELECT * FROM users LEFT JOIN groups ON groups.id_group = users.id_group WHERE username = '" + username + "' AND password = MD5('" + password + "');");

            while(set.next()) {
                User user = new User(set.getString("username"), set.getString("firstname"), set.getString("lastname"));
                user.setUserId(set.getInt("id_user"));
                
                Group group = new Group(set.getString("name"));
                group.setGroupId(set.getInt("id_group"));
                
                user.setGroup(group);
                
                return user;
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }
    
    @Override
    public User getById(int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
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
            
            return st.execute("UPDATE users SET username = '" + user.getUsername() +  "', firstname = '" + user.getFirstName() + "', lastname = '" + user.getLastName() + "', id_group = " + user.getGroup().getGroupId() + ", password = MD5('" + user.getPassword() + "') WHERE id_user = " + user.getUserId() + ";");
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
}
