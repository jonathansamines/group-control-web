package com.jonathansamines.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Index
 * @author jonathansamines
 */
public class ProtectionFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        Object user = req.getSession().getAttribute("user");
        
        
        if (user == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        req.setAttribute("servletPath", req.getServletPath());
        
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
