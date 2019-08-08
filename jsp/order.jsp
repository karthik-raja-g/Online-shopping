<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.List" %>
<%@ page import ="model.Cart" %>
<html>
    <head>
        <title>ORDER MENU</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/orderStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">ORDER MENU</h1>
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
        <div style="text-align: center">
        <div id = "orderBox" class = "box">
            <form method = "post" action = "selectCarts">
                <h4 align = "center"> AVAILABLE CARTS</h4> 
                <table align = "center" border = 0 cellpadding=5>
                    <tr>
                        <th>Cart ID</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Created Date</th>
                        <th>Modified Date</th>
                        <th>Choice</th>
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
                                        <td width ="10%">
                                            <input type = "checkbox" name = "cartId" value = "<%=cart.getCartId()%>">
                                        </td>
                                    </tr>
                                <% } %>            
                    </table>
                    <div style="text-align: center">
                            <br><button type="submit" class="btn btn-success">
                            <span class="glyphicon glyphicon-plus"></span> Add Order</button>
                    </div>
                </form>
            </div>
            </div>
            <div style="text-align: center">
                <form method = "get" action = "viewOrders">
                    <br><button type="submit" class="btn btn-success">
                    <span class="glyphicon glyphicon-eye"></span> View orders</button>
                </form>
            </div>
      </body>
</html>
                                    
