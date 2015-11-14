<%@page import="com.jonathansamines.dao.models.Group"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE>
<html lang="es">
  <head>
    <%@include file="../partials/_meta.jsp" %>

    <title>Mantenimiento de Grupos</title>

    <%@include file="../partials/_includes.jsp" %>
    <% ArrayList<Group> groups = (ArrayList<Group>)request.getAttribute("groups"); %>
  </head>
  <body>
    <div class="container">
      <%@include file="../partials/_header.jsp" %>

      <div class="row">
          <div class="col-md-12">
              <div class="text-right">
                  <a class="btn btn-success" href="<%= request.getContextPath() %>/groups/create">Crear Grupo</a>
              </div>

              <table class="table table-striped">
                  <caption>Grupos de Usuarios</caption>
                  <thead>
                      <tr>
                          <td>Id Grupo</td>
                          <td>Nombre del Grupo</td>
                          <td>-</td>
                      </tr>
                  </thead>
                  <tbody>
                      <% for(Group group : groups) { %>
                        <tr>
                            <td><%= group.getGroupId() %></td>
                            <td><%= group.getName() %></td>
                            <td>
                                <a class="btn btn-primary btn-xs" href="<%= request.getContextPath() %>/groups/update?groupId=<%= group.getGroupId() %>">Modificar</a>
                            </td>
                        </tr>
                      <% } %>
                  </tbody>
              </table>
          </div>
      </div>

      <%@include file="../partials/_footer.jsp" %>
    </div>
</body>
</html>