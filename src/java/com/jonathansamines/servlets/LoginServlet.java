package com.jonathansamines.servlets;

import com.jonathansamines.dao.models.User;
import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

import com.jonathansamines.dao.repository.UserRepository;

/**
 * Index
 * @author jonathansamines
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {       
        if (request.getSession().getAttribute("user") == null) {
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            return;
        }
        
        response.sendRedirect("/");
    }
    
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = (String)request.getAttribute("username");
        String password = (String)request.getAttribute("password");
        
        UserRepository repository = new UserRepository();
        User user = repository.validateCredentials(username, password);
        
        if(user == null) {
            request.setAttribute("message", "Credenciales de usuario incorrectas.");
            request.getRequestDispatcher("views/login.jsp").forward(request, response);
            
            return;
        }
        
        request.getSession().setAttribute("user", user);
        response.sendRedirect("/");
    }
}
