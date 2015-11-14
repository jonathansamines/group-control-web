package com.jonathansamines.servlets;

import com.jonathansamines.dao.models.Group;
import com.jonathansamines.dao.models.Permission;
import com.jonathansamines.dao.repository.GroupRepository;
import com.jonathansamines.dao.repository.PermissionRepository;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "GroupUpdate", urlPatterns = { "/groups/update" })
public class GroupUpdateServlet extends HttpServlet {
    private GroupRepository repository;
    private PermissionRepository permission;
    
    public GroupUpdateServlet() {
        this.repository = new GroupRepository();
        this.permission = new PermissionRepository();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        Group group = this.repository.getById(groupId);
        
        request.setAttribute("permissions", this.permission.get());
        request.setAttribute("group", group);
        request.getRequestDispatcher("../views/groups/updateGroup.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        String groupname = request.getParameter("groupname");
        String []permissions = request.getParameterValues("permissions");

        Group group = new Group(groupname);
        group.setGroupId(groupId);
        
        for (String permission : permissions) {
            int permissionId = Integer.parseInt(permission);
            Permission assigned = new Permission();
            assigned.setPermissionId(permissionId);
            group.addPermission(assigned);
        }
        
        if (repository.update(group)) {
            request.setAttribute("message", "Grupo modificado correctamente.");
            response.sendRedirect(request.getContextPath() + "/groups");
            return;
        }
        
        Group originalGroup = this.repository.getById(groupId);
        
        request.setAttribute("permissions", this.permission.get());
        request.setAttribute("group", originalGroup);
        request.setAttribute("message", "Error al modificar el grupo.");
        request.getRequestDispatcher("../views/groups/updateGroup.jsp").forward(request, response);
    }
}
