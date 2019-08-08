<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="model.Product" %>
<html>
    <head>
        <title>PRODUCT DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/productInfoStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">PRODUCT DETAILS</h1>
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
    <% Product product= (Product)request.getAttribute("product"); %>
        <div style="text-align: center">
            <div id = "productBox" class = "box">
                <p>Product Id: <%=product.getId()%></p>
                <p>Name: <%=product.getName()%><p/>
                <p>Description: <%=product.getDescription()%><p/>
                <p>Price: <%=product.getPrice()%> Rs<p/>
                <p>SKU: <%=product.getSKU()%><p/>
                <p>Creator Id: <%=product.getUser().getUserId()%><p/>
                <p>Is Available: <%=product.getStatus()%><p/>
                <p>Created Date: <%=product.getCreated()%><p/>
                <p>Modified Date: <%=product.getModified()%><p/>
            </div>
        </div>
    </body>
</html>
