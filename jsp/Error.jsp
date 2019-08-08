<html>
    <head>
        <title>Error page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/errorStyle.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div style="text-align: center">
            <div id = "errorBox" class = "box">
                <center>
                    <h2>Oops! Something went wrong</h2>
                    <p><h4>${error}</h4>
                </center>
            </div>
        </div>
        <div class ="logo" >
        </div>
        <p><div class="back-button">
            <button type="submit" onclick="history.back()" class="btn btn-danger">
            <span class="glyphicon glyphicon-circle-arrow-left"></span> Back </button>
        </div></p>
        <form action = "home">
            <div class="home-button">
                <button type = "Submit" class="btn btn-danger">
                <span class="glyphicon glyphicon-home"></span> Home</button>
            </div>
        </form>
    </body>
</html>

