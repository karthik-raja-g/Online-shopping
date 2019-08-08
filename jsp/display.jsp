<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.List" %>
<%@ page import ="model.Role" %>
<html>
    <head>
        <title>ROLE DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/displayStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">AVAILABLE ROLES</h1>
		            </div>
	            </div>
            </div>
            <div class="back-button">
                <button type="submit" onclick="history.back()" class="btn btn-danger">
                          <span class="glyphicon glyphicon-circle-arrow-left"></span> Back</button>
            </div>
            <form action = "logout">
                <div class="logout-button">
                    <button type = "Submit" class="btn btn-danger">
                    <span class="glyphicon glyphicon-log-out"></span> Log out</button>
                </div>
            </form>
            <form action = "home">
                <div class="home-button">
                    <button type = "Submit" class="btn btn-danger">
                    <span class="glyphicon glyphicon-home"></span> Home</button>
                </div>
            </form>
        </div>
        <table align = "center" border = 0 cellpadding=5>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Description</th>
                <th>Status</th>
                <th>Created Date</th>
                <th>Modified Date</th>
                <th colspan="2">Actions</th>                
            </tr>
                <%
                    List<Role> roles = (List<Role>)request.getAttribute("roleList");
                    for(Role role : roles) {%>
                            <tr>
                                <td><%=role.getRoleId()%></td>
                                <td><%=role.getRoleName()%></td>
                                <td><%=role.getDescription()%></td>
                                <td><%=role.getIsActive()%></td>
                                <td><%=role.getCreatedDate()%></td>
                                <td><%=role.getModifiedDate()%></td>
                                <td>
                                    <form method = "post" action = "update">
                                        <input type ="hidden" name = "roleId" value ="<%=role.getRoleId()%>" />
                                        <button type = "Submit" class="btn btn-info">
                                        <span class="glyphicon glyphicon-pencil"></span> Edit</button>
                                    </form>
                                </td>
                                <td>
                                    <form method = "post" action = "delete">
                                        <input type ="hidden" name = "roleId" value ="<%=role.getRoleId()%>" />
                                        <button type = "Submit" class="btn btn-info">
                                        <span class="glyphicon glyphicon-trash"></span> Delete</button>
                                    </form>
                                </td>
                            </tr>
                  <%  } %>
        </table>
        <div style="text-align: center">
            <form method = "post" action = "role">
                <br><button type="submit" class="btn btn-success">
                <span class="glyphicon glyphicon-plus"></span> Add role</button>
            </form>
        </div>
    </body>
</html>
