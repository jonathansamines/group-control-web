package com.jonathansamines.servlets;

import com.jonathansamines.dao.models.Group;
import com.jonathansamines.dao.repository.GroupRepository;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

/**
 * Index
 * @author jonathansamines
 */
@WebServlet(name = "GroupDelete", urlPatterns = { "/groups/delete" })
public class GroupDeleteServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int groupId = Integer.parseInt(request.getParameter("groupId"));
        GroupRepository repository = new GroupRepository();
        
        repository.delete(groupId);
        response.sendRedirect(request.getContextPath() + "/groups");
    }
}
