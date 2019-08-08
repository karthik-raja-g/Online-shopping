<html>
    <head>
        <title>PRODUCT MENU</title>
        <meta charset="utf-8">

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/productStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">PRODUCT MENU</h1>
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
            <div id = "productBox" class = "box">
                <form method = "post" action = "productDetails">
                    <p><input type = "text" placeholder = "Product name" name = "name" required/></p>
                    <p><input type = "text" placeholder = "Description"name = "description" required /></p>
                    <p><input type = "text" placeholder = "Price"name = "price" required/></p>
                    <!--<p><input type = "text" placeholder = "Image URL"name ="url"-->
                    <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-plus"></span> Add</button><br>
                </form>
            </div>
        </div>
        <div style="text-align: center">
            <form method = "get" action = "viewProducts">
                <br><button type="submit" class="btn btn-success">
                <span class="glyphicon glyphicon-eye-open"></span> View all products</button>
            </form>
        </div>
    </body>
</html>
