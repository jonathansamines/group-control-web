<!DOCTYPE>
<html lang="es">
  <head>
    <%@include file="partials/_meta.jsp" %>
    <title>Inicio de Sesión</title>
    <%@include file="partials/_includes.jsp" %>
  </head>

  <body>
      <%@include file="partials/_header.jsp" %>
      
      <div class="container">
          <br>
          <br>
          <br>
          <div class="row">
            <div class="col-md-4 col-md-offset-4">
                <form method="POST" action="login">
                    <div class="form-group">
                        <label for="username">Nombre de Usuario</label>
                        <input type="text" class="form-control" id="username" name="username" placeholder="Usuario">
                    </div>

                    <div class="form-group">
                        <label for="password">Password</label>
                        <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                    </div>
                    
                    <% if (request.getAttribute("message") != null) { %>
                        <div class="form-group">
                            <p class="alert alert-warning"><%= request.getAttribute("message") %></p>
                        </div>
                    <% } %>

                  <button type="submit" class="btn btn-primary">Iniciar Sesión</button>
                </form>
            </div>
        </div>
      </div>

      <%@include file="partials/_footer.jsp" %>
    </div>
</body>
</html>