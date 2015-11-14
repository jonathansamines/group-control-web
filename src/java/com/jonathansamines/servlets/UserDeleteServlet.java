package com.jonathansamines.servlets;

import com.jonathansamines.dao.repository.UserRepository;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet(name = "UserDelete", urlPatterns = { "/users/delete" })
public class UserDeleteServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        
        UserRepository repository = new UserRepository();
        
        repository.delete(userId);
        
        response.sendRedirect(request.getContextPath() + "/users");
    }
}

