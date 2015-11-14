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
@WebServlet(name = "Groups", urlPatterns = { "/groups" })
public class GroupsServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("views/groups.jsp").forward(request, response);
    }
}
