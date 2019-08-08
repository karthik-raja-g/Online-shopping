<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.List" %>
<%@ page import ="model.Role" %>
<html>
    <head>
        <title>ROLE DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/updateStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">ROLE UPDATE</h1>
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
    <% Role role = (Role)request.getAttribute("update"); %>
        <div style="text-align: center">
            <div id = "roleBox" class = "box">
                <form method = "post" action = "updatePage">
                    <input type = "hidden" name = "roleId" value = "<%=role.getRoleId()%>"/>
                    <input type = "hidden" name = "status" value = "<%=role.getIsActive()%>"/>
                    <input type = "hidden" name = "created" value = "<%=role.getCreatedDate()%>"/>
                    <p>Name <input type = "text" placeholder = "Name" name = "name" value = "<%=role.getRoleName()%>" required /></p>
                    <p>Description <input type = "text" placeholder ="Description" name = "description" value = "<%=role.getDescription()%>" required/></p>
                    <button type="submit" class="btn btn-success">
                        <span class="glyphicon glyphicon-refresh"></span> Update</button><br>
                </form>
            </div>
        </div>
    </body>
</html>
    
    
