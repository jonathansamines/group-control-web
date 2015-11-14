<%@page import="com.jonathansamines.dao.models.Permission"%>
<%@page import="com.jonathansamines.dao.models.User"%>

<%
  User user = (User)request.getSession().getAttribute("user");
%>

<div class="header clearfix">
    <nav>
      <ul class="nav nav-pills pull-right">
        

        <%
            if (user != null) {
                for(Permission permission : user.getGroup().getPermissions()) { %>
                    <li role="presentation" class="<%= (permission.getPath().equals(request.getAttribute("servletPath")) ? "active" : "") %>">
                        <a href="<%= request.getContextPath() + permission.getPath() %>"><%= permission.getDisplay() %></a>
                    </li>
            <% } %>
            <li role="presentation">
                <a href="<%= request.getContextPath() + "/logout" %>">Logout</a>
            </li>
           <% } %>
      </ul>
    </nav>
    <h3 class="text-muted">Control de Permisos</h3>
</div>