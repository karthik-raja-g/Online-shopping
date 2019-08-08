<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.List" %>
<%@ page import ="model.Role" %>
<html>
    <head>
        <title>USER MENU</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/userStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
       
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">USER MENU</h1>
		            </div>
	            </div>
            </div>
            <div class="back-button">
                <button type="submit" onclick="history.back()" class="btn btn-danger">
                <span class="glyphicon glyphicon-circle-arrow-left"></span> Back </button>
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
            <div style="text-align: center">
            <div id = "userBox" class = "box">
                <form method = "post" action = "userDetails">
                    <p><input type = "text" placeholder = "Name" pattern ="^[a-zA-Z]*$" name = "name" required/>
                    <p><input type = "text" placeholder = "Username" pattern = "[a-zA-Z0-9]+" name = "username" required />
                    <p><input type = "password" placeholder = "Password" name = "password" required/>
                    <p><input type = "email" placeholder = "Email Id" name = "email" required/>
                    <p><input type = "tel" placeholder = "Phone" pattern = "(0/91)?[7-9][0-9]{9}" name = "phone" required/>
                    <p><input type = "text" placeholder = "Address" pattern = "[a-zA-Z0-9]+" name = "address" required/><p>
                    <p><strong>SELECT ROLES</strong></p>
                    <% List<Role> roles = (List<Role>)request.getAttribute("roleList"); %>

                    <% for(Role role : roles) { %>
                        <input type = "checkbox" name = "roleId" value = "<%=role.getRoleId()%>"><%=role.getRoleName()%>&nbsp;&nbsp;
                    <% } %>
                    <p><div style="text-align: center">
                        <button type="submit" class="btn btn-success">
                            <span class="glyphicon glyphicon-plus"></span> Add
                        </button><br>
                    </div>
                </form>
            </div>
            </div>
            <div style="text-align: center">
                <form method = "get" action = "viewUsers">
                    <br><button type="submit" class="btn btn-success">
                    <span class="glyphicon glyphicon-eye-open"></span> View all users</button>
                </form>
            </div>
    </body>
</html>

