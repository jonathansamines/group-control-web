<%@page import="com.jonathansamines.dao.models.Group"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE>
<html lang="es">
  <head>
    <%@include file="../partials/_meta.jsp" %>

    <title>Creación de Grupo</title>

    <%@include file="../partials/_includes.jsp" %>

    <% ArrayList<Group> groups = (ArrayList<Group>)request.getAttribute("groups"); %>
  </head>
  <body>
    <div class="container">
      <%@include file="../partials/_header.jsp" %>

      <div class="row">
          <div class="col-md-12">
            <form method="POST" action="create">
                <div class="form-group">
                    <label for="groupname">Nombre de Grupo</label>
                    <input type="text" class="form-control" id="groupname" name="groupname" placeholder="Nombre del Grupo" required>
                </div>

                <button type="submit" class="btn btn-success">Crear Grupo</button>
            </form>
              
            <% if (request.getAttribute("message") != null) { %>
                <div class="form-group">
                    <p class="alert alert-warning"><%= request.getAttribute("message") %></p>
                </div>
            <% } %>
          </div>
      </div>

      <%@include file="../partials/_footer.jsp" %>
    </div>
</body>
</html>