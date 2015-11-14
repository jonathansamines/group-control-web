<%@page import="com.jonathansamines.dao.models.Group"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE>
<html lang="es">
  <head>
    <%@include file="../partials/_meta.jsp" %>

    <title>Actualización de Grupo</title>

    <%@include file="../partials/_includes.jsp" %>
    <% Group group = (Group)request.getAttribute("group"); %>
    <% ArrayList<Permission> permissions = (ArrayList<Permission>)request.getAttribute("permissions"); %>
  </head>
  <body>
    <div class="container">
      <%@include file="../partials/_header.jsp" %>

      <div class="row">
          <div class="col-md-12">
            <form method="POST" action="update">
                <input type="hidden" name="groupId" value="<%= group.getGroupId() %>">
                <div class="form-group">
                    <label for="groupname">Nombre de Grupo</label>
                    <input type="text" class="form-control" id="groupname" name="groupname" placeholder="Nombre del Grupo" value="<%= group.getName() %>" required>
                </div>
                
                <div class="form-group">
                    <label for="permissions">Asignar Permisos</label>
                    <select class="form-control" name="permissions" id="permissions" multiple="true" required>
                        <% for(Permission permission : permissions) {
                            Boolean hasPermissionAssigned = false;
                            ArrayList<Permission> groupPermissions = group.getPermissions();
                            
                            for(Permission gpermission: groupPermissions) {
                                if(gpermission.getPermissionId() == permission.getPermissionId()) {
                                    hasPermissionAssigned = true;
                                    break;
                                }
                            }
                        %>
                            <option value="<%= permission.getPermissionId() %>" <%= hasPermissionAssigned ? "selected" : "" %>>
                                <%= permission.getDisplay()%> (<%= permission.getPath() %>)
                            </option>
                        <% } %>
                    </select>
                </div>
                    
                <% if (request.getAttribute("message") != null) { %>
                    <div class="form-group">
                        <p class="alert alert-warning"><%= request.getAttribute("message") %></p>
                    </div>
                <% } %>

                <button type="submit" class="btn btn-success">Modificar Grupo</button>
            </form>
          </div>
      </div>

      <%@include file="../partials/_footer.jsp" %>
    </div>
</body>
</html>