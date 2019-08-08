<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.List" %>
<%@ page import ="model.Order" %>
<html>
    <head>
        <title>ORDER DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/orderDisplayStyle.css">
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
			            <h1 class="knockout">USER ORDERS</h1>
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
                <th>Cost</th>
                <th>Quantity</th>
                <th>Created Date</th>
                <th>Modified Date</th>
                <th>Status</th>
                <th colspan="2">Actions</th>
            </tr>
                <%
                    List<Order> orders = (List<Order>)request.getAttribute("orderList");
                    for(Order order : orders) {%>
                            <tr>
                                <td><%=order.getOrderId()%></td>
                                <td><%=order.getCost()%></td>
                                <td><%=order.getQuantity()%></td>
                                <td><%=order.getCreatedDate()%></td>
                                <td><%=order.getModifiedDate()%></td>
                                <td><%=order.getStatus()%></td>
                                <td>
                                    <form method = "post" action = "orderDelete">
                                        <input type ="hidden" name = "orderId" value ="<%=order.getOrderId()%>" />
                                        <button type = "Submit" class="btn btn-info">
                                        <span class="glyphicon glyphicon-trash"></span> Delete</button>
                                    </form>
                                </td>
                                <td>
                                    <form method = "get" action = "showOrderDetails">
                                        <input type ="hidden" name = "orderId" value ="<%=order.getOrderId()%>" />
                                        <button type = "Submit" class="btn btn-info">
                                        <span class="glyphicon glyphicon-eye"></span> View details</button>
                                    </form>
                                </td>
   
                            </tr>
                        <%}%>
                </table>
        <div style="text-align: center">
            <form method = "post" action = "order">
                <br><button type="submit" class="btn btn-success">
                <span class="glyphicon glyphicon-plus"></span> Add order</button>
            </form>
        </div>
    </body> 
</html>                     
