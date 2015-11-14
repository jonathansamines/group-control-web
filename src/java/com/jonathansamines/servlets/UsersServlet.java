package com.jonathansamines.servlets;

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
@WebServlet(name = "Users", urlPatterns = { "/users" })
public class UsersServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/users.jsp").forward(request, response);
    }
}
