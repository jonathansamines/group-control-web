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
        ArrayList<Group> groups = new ArrayList<>();

        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            ResultSet set = st.executeQuery("SELECT * FROM Group LEFT JOIN Permissions ON Permissions.id_group = Group.id_group;");

            while(set.next()) {
                Group group = new Group(set.getString("name"));
                group.setGroupId(set.getInt("id_group"));
                
                Permission permission = new Permission(set.getString("name"), null);
                permission.setPermissionId(set.getInt("id_permission"));
                
                int index = groups.indexOf(group);
                if (index == -1) {
                    groups.add(group);
                }
                
                group.addPermission(permission);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return groups;
    }

    @Override
    public Group getById(int id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean create(Group group) {
        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            return st.execute("INSERT INTO Group VALUES('" + group.getName() + "')");
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    @Override
    public boolean update(Group group) {
        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            return st.execute("UPDATE Group SET name = '" + group.getName() + "';");
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    
}
