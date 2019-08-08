<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.Set" %>
<%@ page import ="model.OrderDetails" %>
<html>
    <head>
        <title>ORDER DETAILS DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/orderDetailsdisplayStyle.css"
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <style>

        </style>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">ORDER DETAILS</h1>
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
        </style>
        <table align = "center" border = 0 cellpadding=5>
            <tr>
                <th>ID</th>
                <th>Product Name</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Product ID</th>
                <th>Order ID</th>
            </tr>
                <%
                    Set<OrderDetails> orderItems = (Set<OrderDetails>)request.getAttribute("orderDetailList");
                    for(OrderDetails orderDetail : orderItems) {%>
                            <tr>
                                <td><%=orderDetail.getId()%></td>
                                <td><%=orderDetail.getProdName()%></td>
                                <td><%=orderDetail.getPrice()%></td>
                                <td><%=orderDetail.getQuantity()%></td>
                                <td><%=orderDetail.getProduct().getId()%></td>
                                <td><%=orderDetail.getOrder().getOrderId()%></td>
                            </tr>
                        <%}%>
             </table>
    </body>
</html>    
                                

