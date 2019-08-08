<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="model.Product" %>
<html>
    <head>
        <title>PRODUCT UPDATE</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/updateProductStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">PRODUCT UPDATE</h1>
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
    <% Product product= (Product)request.getAttribute("update"); %>
        <div style="text-align: center">
            <div id = "productBox" class = "box">
                <form action = "productUpdatePage" method = "post">
                    <input type = "hidden" name = "prodId" value = "<%=product.getId()%>"/>
                    <input type = "hidden" name = "status" value = "<%=product.getStatus()%>"/>
                    <input type = "hidden" name = "created" value = "<%=product.getCreated()%>"/>
                    <input type = "hidden" name = "SKU" value = "<%=product.getSKU()%>"/>
                    <p><input type = "hidden" name = "userId" value = "<%=product.getUser().getUserId()%>"/></p>
                    <p>Product <input type = "text" placeholder = "Product name" name = "name" value = "<%=product.getName()%>" required/></p>
                    <p>Description <input type = "text" placeholder = "Description"name = "description" value = "<%=product.getDescription()%>"required/></p>
                    <p>Price <input type = "text" placeholder = "Price"name = "price" value = "<%=product.getPrice()%>" required/></p>
                    <button type="submit" class="btn btn-success">
                        <span class="glyphicon glyphicon-refresh"></span> Update</button><br>
                </form>
            </div>
        </div>
    </body>
</html>
    
    
