
<%@page import="java.util.ArrayList"%>

<!DOCTYPE>
<html lang="es">
  <head>
    <%@include file="partials/_meta.jsp" %>

    <title>Usuarios</title>

    <%@include file="partials/_includes.jsp" %>
    <% ArrayList<User> users = (ArrayList<User>)request.getAttribute("users"); %>
  </head>
  <body>
      <div class="container">
          <%@include file="partials/_header.jsp" %>
          
          <div class="row">
              <div class="col-md-12">
                  <table class="table table-striped">
                      <caption>Usuarios</caption>
                      <thead>
                          <tr>
                              <td>Nombre de Usuario</td>
                              <td>Nombre</td>
                              <td>Apellido</td>
                              <td>-</td>
                          </tr>
                      </thead>
                      <tbody>
                          <% for(User u : users) { %>
                            <tr>
                                <td><%= u.getUsername() %></td>
                                <td><%= u.getFirstName()%></td>
                                <td><%= u.getLastName() %></td>
                                <td>-</td>
                            </tr>
                          <% } %>
                      </tbody>
                  </table>
              </div>
          </div>
          
          <%@include file="partials/_footer.jsp" %>
      </div>
    </div>
</body>
</html>