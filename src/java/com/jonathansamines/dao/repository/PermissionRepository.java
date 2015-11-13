package com.jonathansamines.dao.repository;

import com.jonathansamines.dao.ConnectionManager;
import com.jonathansamines.dao.models.Permission;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * PermissionRepository, maps the operations with the Permission model
 * @author jonathansamines
 */
public class PermissionRepository implements IRepository<Permission> {

    @Override
    public ArrayList get() {
        ArrayList<Permission> permissions = new ArrayList<>();

        try(Connection connection = ConnectionManager.getConnection();
            Statement st = connection.createStatement()) {
            ResultSet set = st.executeQuery("SELECT * FROM Permission;");

            while(set.next()) {
                Permission permission = new Permission(set.getString("name"));
                permission.setPermissionId(set.getInt("id_permission"));
                
                permissions.add(permission);
            }
        }catch(SQLException e) {
            e.printStackTrace();
        }
        
        return permissions;
    }

    @Override
    public Permission getById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean create(Permission o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Permission o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
