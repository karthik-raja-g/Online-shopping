<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="model.User" %>
<html>
    <head>
        <title>USER UPDATE</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/updateUserStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">USER UPDATE</h1>
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
    <% User user= (User)request.getAttribute("update"); %>
        <div style="text-align: center">
            <div id = "userBox" class = "box">
                <form method = "post" action = "userUpdatePage">
                    <input type = "hidden" name = "userId" value = "<%=user.getUserId()%>"/>
                    <input type = "hidden" name = "status" value = "<%=user.getIsActive()%>"/>
                    <input type = "hidden" name = "created" value = "<%=user.getCreated()%>"/>
                    <p>Name   <input type = "text" placeholder = "Name" name = "name" pattern ="^[a-zA-Z]*$" value = "<%=user.getName()%>" required/></p>
                    <p>Username <input type = "text" placeholder = "Username" name = "username" pattern = "[a-zA-Z0-9]+" value = "<%=user.getUserName()%>"required/></p>
                    <p>Password <input type = "password" placeholder = "Password"name = "password" value = "<%=user.getPassword()%>"required/></p>
                    <p>Email Id <input type = "email" placeholder = "Email Id"name = "email" value = "<%=user.getEmail()%>" required/></p>
                    <p>Phone <input type = "tel" placeholder = "Phone"name = "phone" pattern = "(0/91)?[7-9][0-9]{9}" value = "<%=user.getPhone()%>" required/></p>
                    <p>Address <input type = "text" placeholder = "Address"name = "address" pattern = "[a-zA-Z0-9]+" value = "<%=user.getAddress()%>" required/></p>
                    <button type="submit" class="btn btn-success">
                        <span class="glyphicon glyphicon-refresh"></span> Update</button><br>
                </form>
            </div>
        </div>
    </body>
</html>
    
    
