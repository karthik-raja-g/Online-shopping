<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import ="java.util.List" %>
<%@ page import ="model.Product" %>
<html>
    <head>
        <title>PRODUCT DISPLAY</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/displayProductsStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">AVAILABLE PRODUCTS</h1>
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

        <% List<Product> products = (List<Product>)request.getAttribute("productList"); %>
            <% for(Product product : products) {%>
                <div id="pattern" class="pattern">
                    <ul class="list img-list">
		                <li>
                            <div class="inner">
				                <div class="li-img">
					                <img src="https://www.devpolicy.org/wp-content/uploads/2015/02/image3.jpg"alt="Image Alt Text"/>
				                </div>
				                <div class="li-text" id="info">
					                <h4 class="li-head"><%=product.getName()%></h4>
					                <p class="li-sub">Description: <%=product.getDescription()%></p>
					                <p class="li-sub">Price: <%=product.getPrice()%> Rs</p>
                                    <div class="prod-info">
                                        <form method = "get" action = "productInfo">
                                            <input type ="hidden" name = "prodId" value ="<%=product.getId()%>" />
                                            <button type = "Submit" class="btn btn-info">
                                            <span class="glyphicon glyphicon-info-sign"></span></button>
                                        </form>
                                    </div>
                                    <form method = "post" action = "addToCart">
                                        <input type ="hidden" name = "prodId" value ="<%=product.getId()%>" />
                                        <button type = "Submit" class="btn btn-info">
                                        <span class="glyphicon glyphicon-shopping-cart"></span> Add to cart</button>
                                    </form>
                                    <c:if test = "${role == 'admin'}"> 
                                        <div class="admin">
                                            <form method = "post" action = "productUpdate">
                                                <input type ="hidden" name = "prodId" value ="<%=product.getId()%>" />
                                                <button type = "Submit" class="btn btn-info">
                                                <span class="glyphicon glyphicon-pencil"></span> Edit</button>
                                            </form>
                                            <form method = "post" action = "productDelete">
                                                <input type ="hidden" name = "prodId" value ="<%=product.getId()%>" />
                                                <button type = "Submit" class="btn btn-info">
                                                <span class="glyphicon glyphicon-trash"></span> Delete</button>
                                            </form>
                                        </div>         
                                    </c:if>                  
				                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            <%}%>
        <c:if test = "${role == 'admin'}">
            <div style="text-align: center">
                <form method = "post" action = "product">
                    <br><button type="submit" class="btn btn-success">
                    <span class="glyphicon glyphicon-plus"></span> Add product</button>
                </form>
            </div>
        </c:if>
    </body> 
</html>                     
