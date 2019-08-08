<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="model.Cart" %>
<html>
    <head>
        <title>CART UPDATE</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/updateCartStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">CART UPDATE</h1>
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
    <% Cart cart= (Cart)request.getAttribute("update"); %>
        <div style="text-align: center">
            <div id = "cartBox" class = "box">
                <form action = "cartUpdatePage" method = "post">
                    <input type = "hidden" name = "cartId" value = "<%=cart.getCartId()%>"/>
                    <input type = "hidden" name = "prodId" value = "<%=cart.getProduct().getId()%>"/>
                    <input type = "hidden" name = "created" value = "<%=cart.getCreated()%>"/>
                    <input type = "hidden" name = "userId" value = "<%=cart.getUser().getUserId()%>"/>
                    <p><input type = "text" placeholder = "New quantity" name = "quantity" required/></p>
                    <button type="submit" class="btn btn-success">
                        <span class="glyphicon glyphicon-refresh"></span> Update</button><br>
                </form>
            </div>
        </div>
    </body>
</html>
    
    
