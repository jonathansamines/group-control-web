package com.jonathansamines.servlets;

import com.jonathansamines.dao.models.Group;
import com.jonathansamines.dao.repository.GroupRepository;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "GroupUpdate", urlPatterns = { "/groups/update" })
public class GroupUpdateServlet extends HttpServlet {
    private GroupRepository repository;
    
    public GroupUpdateServlet() {
        this.repository = new GroupRepository();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        Group group = this.repository.getById(groupId);
        
        request.setAttribute("group", group);
        request.getRequestDispatcher("../views/groups/updateGroup.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));

        String groupname = request.getParameter("groupname");
        Group group = new Group(groupname);
        group.setGroupId(groupId);
        
        if (repository.update(group)) {
            request.setAttribute("message", "Grupo modificado correctamente.");
            response.sendRedirect(request.getContextPath() + "/groups");
            return;
        }
        
        request.setAttribute("message", "Error al modificar el grupo.");
        request.getRequestDispatcher("../views/groups/updateGroup.jsp").forward(request, response);
    }
}
