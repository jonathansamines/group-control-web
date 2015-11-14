package com.jonathansamines.servlets;

import com.jonathansamines.dao.models.Group;
import com.jonathansamines.dao.models.User;
import com.jonathansamines.dao.repository.GroupRepository;
import com.jonathansamines.dao.repository.UserRepository;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "UserUpdate", urlPatterns = { "/users/update" })
public class UserUpdateServlet extends HttpServlet {
    private GroupRepository groups;
    private UserRepository users;
    
    public UserUpdateServlet() {
        this.groups = new GroupRepository();
        this.users = new UserRepository();
    }
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("user", users.getById(Integer.parseInt(request.getParameter("userId"))));
        request.setAttribute("groups", groups.get());
        request.getRequestDispatcher("../views/users/updateUser.jsp").forward(request, response);
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String firstname = request.getParameter("firstname");
        String lastname = request.getParameter("lastname");
        
        if (username.equals("") || firstname.equals("")) {
            request.setAttribute("message", "Error al crear el usuario");

            request.setAttribute("groups", groups.get());
            request.getRequestDispatcher("../views/users/updateUser.jsp").forward(request, response);
            
            return;
        }
        
        int groupId = Integer.parseInt(request.getParameter("group"));
        
        User user = new User(username, firstname, lastname);
        
        Group group = new Group(null);
        group.setGroupId(groupId);

        user.setGroup(group);
        
        if (this.users.update(user)) {
            request.setAttribute("message", "Usuario creado correctamente.");
            System.out.println(request.getContextPath() + "/users");
            response.sendRedirect(request.getContextPath() + "/users");
            
            return;
        }
        
        request.setAttribute("message", "Error al crear el usuario");

        request.setAttribute("groups", groups.get());
        request.getRequestDispatcher("../views/users/updateUser.jsp").forward(request, response);
    }
}
