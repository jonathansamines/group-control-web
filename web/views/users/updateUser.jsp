
<%@page import="com.jonathansamines.dao.models.Group"%>
<%@page import="com.jonathansamines.dao.models.User"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE>
<html lang="es">
  <head>
    <%@include file="../partials/_meta.jsp" %>

    <title>Modificar Usuario</title>

    <%@include file="../partials/_includes.jsp" %>

    <% ArrayList<Group> groups = (ArrayList<Group>)request.getAttribute("groups"); %>
    <% User u = (User)request.getAttribute("user"); %>
  </head>
  <body>
      <div class="container">
          <%@include file="../partials/_header.jsp" %>
          
          <div class="row">
              <div class="col-md-12">
                <form method="POST" action="">
                    <div class="form-group">
                        <label for="username">Nombre de Usuario</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Usuario" required value="<%= u.getUsername() %>">
                    </div>
                    
                    <div class="form-group">
                        <label for="firstname">Nombre</label>
                        <input type="text" class="form-control" id="firstname" name="firstname" placeholder="Nombre" required value="<%= u.getFirstName() %>">
                    </div>
                    
                    <div class="form-group">
                        <label for="lastname">Apellidos</label>
                        <input type="text" class="form-control" id="lastname" name="lastname" placeholder="Apellidos" value="<%= u.getLastName() %>">
                    </div>
                    
                    <div class="form-group">
                        <label for="group">Grupo</label>
                        <select name="group" class="form-control">
                            <% for(Group group : groups) { %>
                            <option <%= u.getGroup().getName().equals(group.getName()) ? "selected": "" %> value="<%= group.getGroupId() %>"><%= group.getName() %></option>
                            <% } %>
                        </select>
                    </div>

                    <% if (request.getAttribute("message") != null) { %>
                        <div class="form-group">
                            <p class="alert alert-warning"><%= request.getAttribute("message") %></p>
                        </div>
                    <% } %>
                    <button type="submit" class="btn btn-success">Modificar Usuario</button>
                </form>
              </div>
          </div>
          
          <%@include file="../partials/_footer.jsp" %>
      </div>
    </div>
</body>
</html>
