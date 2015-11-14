package com.jonathansamines.servlets;

import com.jonathansamines.dao.repository.GroupRepository;
import com.jonathansamines.dao.repository.UserRepository;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "Users", urlPatterns = { "/users" })
public class UsersServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserRepository repository = new UserRepository();
        GroupRepository groups = new GroupRepository();

        request.setAttribute("users", repository.get());
        request.setAttribute("groups", groups.get());
        request.getRequestDispatcher("views/users/users.jsp").forward(request, response);
    }
}
