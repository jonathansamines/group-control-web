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
                    <li role="presentation <%= permission.getPath().equals(request.getContextPath()) %>">
                        <a href="<%= request.getContextPath() + permission.getPath() %>"><%= permission.getName() %></a>
                    </li>
          <% }
            }
          %>
      </ul>
    </nav>
    <h3 class="text-muted">Control de Permisos</h3>
</div>