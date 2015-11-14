<%@page import="com.jonathansamines.dao.models.Group"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE>
<html lang="es">
  <head>
    <%@include file="../partials/_meta.jsp" %>

    <title>Actualización de Grupo</title>

    <%@include file="partials/_includes.jsp" %>
    <% ArrayList<Group> groups = (ArrayList<Group>)request.getAttribute("groups"); %>
    <% Group group = (Group)request.getAttribute("group"); %>

  </head>
  <body>
    <div class="container">
      <%@include file="../partials/_header.jsp" %>

      <div class="row">
          <div class="col-md-12">
            <form method="POST" action="groups">
                <div class="form-group">
                    <label for="groupname">Nombre de Grupo</label>
                    <input type="text" class="form-control" id="groupname" name="groupname" placeholder="Nombre del Grupo" value="<%= group.getName() %>">
                </div>

                <button type="submit" class="btn btn-success">Modificar Grupo</button>
            </form>
          </div>
      </div>

      <%@include file="../partials/_footer.jsp" %>
    </div>
</body>
</html>