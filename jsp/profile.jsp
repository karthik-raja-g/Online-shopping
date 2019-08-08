<%@ page import ="model.User" %>
<html>
    <head>
        <title>User Profile</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/profileStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <% User user = (User)session.getAttribute("user"); %>
        <div class ="jumbotron">
            <h1 align = "center">Welcome Back</h1>
            <h2 align = "center"><%=user.getName()%></h2>
            <form action = "home">
                <div class="home-button">
                    <button type = "Submit" class="btn btn-danger">
                    <span class="glyphicon glyphicon-home"></span> Home</button>
                </div>
            </form>
            <form action = "logout">
                <div class="logout-button">
                    <button type = "Submit" class="btn btn-danger">
                    <span class="glyphicon glyphicon-log-out"></span> Log out</button>
                </div>
            </form>
        </div>
        <div style="text-align: center">
            <div id = "profileBox" class = "box">
                <div class="img">
	                <img src="https://www.lynmark.com.au/wp-content/uploads/2015/07/log.png"alt="Image Alt Text"/><br>
                </div>
                <div class="info">
                    <p>User Id : <%=user.getUserId()%></p>
                    <p>Name : <%=user.getName()%></p>
                    <p>Username : <%=user.getUserName()%></p>
                    <p>Email Id : <%=user.getEmail()%></p>
                    <p>Phone : <%=user.getPhone()%></p>
                    <p>Address : <%=user.getAddress()%></p>
                    <p>Status : <%=user.getIsActive()%></p>
                    <p>Created Date : <%=user.getCreated()%></p>
                    <p>Modified Date : <%=user.getModified()%></p>   
                </div>                 
            </div>
        </div>
    </body>
</html>
    
