package com.jonathansamines.servlets;

import com.jonathansamines.dao.models.Group;
import com.jonathansamines.dao.repository.GroupRepository;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "GroupCreation", urlPatterns = { "/groups/create" })
public class GroupCreationServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("../views/groups/createGroup.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        GroupRepository repository = new GroupRepository();
        
        String groupname = request.getParameter("groupname");
        Group group = new Group(groupname);
        
        if (repository.create(group)) {
            request.setAttribute("message", "Grupo creado correctamente.");
            response.sendRedirect(request.getContextPath() + "/groups");
            return;
        }
        
        request.setAttribute("message", "Error al crear el grupo.");
        request.getRequestDispatcher("../views/groups/createGroup.jsp").forward(request, response);
    }
}
