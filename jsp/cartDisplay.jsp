<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.List" %>
<%@ page import ="model.Cart" %>
<html>
    <head>
        <title>CART DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/cartDisplayStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">AVAILABLE USER CARTS</h1>
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
                <th>Product Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Created Date</th>
                <th>Modified Date</th>
                <th colspan="2">Actions</th>
            </tr>
                <%
                    List<Cart> carts = (List<Cart>)request.getAttribute("userCarts");
                    for(Cart cart : carts) {%>
                            <tr>
                                <td><%=cart.getCartId()%></td>
                                <td><%=cart.getProduct().getName()%></td>
                                <td><%=cart.getProduct().getPrice()%></td>
                                <td><%=cart.getQuantity()%></td>
                                <td><%=cart.getCreated()%></td>
                                <td><%=cart.getModified()%></td>
                                <td>
                                    <form method = "post" action = "cartUpdate">
                                        <input type ="hidden" name = "cartId" value ="<%=cart.getCartId()%>" />
                                        <button type = "Submit" class="btn btn-info">
                                        <span class="glyphicon glyphicon-pencil"></span> Edit</button>
                                    </form>
                                </td>
                                <td>
                                    <form method = "post" action = "cartDelete">
                                        <input type ="hidden" name = "cartId" value ="<%=cart.getCartId()%>" />
                                        <button type = "Submit" class="btn btn-info">
                                        <span class="glyphicon glyphicon-trash"></span> Delete</button>
                                    </form>
                                </td>      
                            </tr>
                        <%}%>
        </table>
        <div style="text-align: center">
            <form method = "get" action = "viewProducts">
                <br><button type="submit" class="btn btn-success">
                <span class="glyphicon glyphicon-plus"></span> Add cart</button>
            </form>
    </body> 
</html>                     
