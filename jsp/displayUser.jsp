<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.List" %>
<%@ page import ="model.User" %>
<html>
    <head>
        <title>USER DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/displayUserStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">AVAILABLE USERS</h1>
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
        <table align = "center" border = 0 cellpadding=20>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Username</th>
                <th>Email Id</th>
                <th>Phone number</th>
                <th>Address</th>
                <th>Active status</th>
                <th>Created Date</th>
                <th>Modified Date</th>
                <th colspan="2">Actions</th>
            </tr>
                <%
                    List<User> users = (List<User>)request.getAttribute("userList");
                    for(User user : users) {%>
                        <tr>
                            <td><%=user.getUserId()%></td>
                            <td><%=user.getName()%></td>
                            <td><%=user.getUserName()%></td>
                            <td><%=user.getEmail()%></td>
                            <td><%=user.getPhone()%></td>
                            <td><%=user.getAddress()%></td>
                            <td><%=user.getIsActive()%></td>
                            <td><%=user.getCreated()%></td>
                            <td><%=user.getModified()%></td>
                            <c:if test = "${role == 'admin'}"> 
                                <td>
                                    <form method = "post" action = "userUpdate">
                                        <input type ="hidden" name = "userId" value ="<%=user.getUserId()%>" />
                                        <button type = "Submit" class="btn btn-info">
                                        <span class="glyphicon glyphicon-pencil"></span> Edit</button>
                                    </form>
                                </td>
                                <td>
                                    <form method = "post" action = "userDelete">
                                        <input type ="hidden" name = "userId" value ="<%=user.getUserId()%>" />
                                        <button type = "Submit" class="btn btn-info">
                                        <span class="glyphicon glyphicon-trash"></span> Delete</button>
                                    </form>
                                </td>   
                            </c:if>   
                        </tr>
                  <%}%>
        </table>
        <div style="text-align: center">
            <form method = "post" action = "user">
                <br><button type="submit" class="btn btn-success">
                <span class="glyphicon glyphicon-plus"></span> Add user</button>
            </form>
        </div>
    </body> 
</html>                     
