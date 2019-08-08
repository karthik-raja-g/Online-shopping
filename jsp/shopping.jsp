<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <body>
        <head>
            <title>MAIN MENU</title>
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1">
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
            <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
        </head>
        <style>
            body{
        background-image: url('https://images.pexels.com/photos/326311/pexels-photo-326311.jpeg?cs=srgb&dl=background-brown-carpentry-326311.jpg&fm=jpg');
            
        background-size: 1500px;
        }
        * {
	            margin: 0;
	            padding: 0;
	            list-style-type: none;
            }


            .jumbotron {
                background: #00cc99
            }

            a{
                font-weight: bold;
            }
            div.logout-button {
                position:relative;
                left: 1400px;
             }

            /* header */
            .border{
	            padding: 5px;
		            background: linear-gradient(110deg, #ffeead 33%, rgba(0, 0, 0, 0) 33%), linear-gradient(110deg, #C5E7D7 34%, #88d8b0 34%);
	            background-size: 400% 400%;
	            height: 180px;
	            background-position: 25% 50%;
	            -webkit-animation: Gradient 15s ease infinite;
	            -moz-animation: Gradient 5s ease infinite;
            }
            .inner-cutout{
	            padding: 40px 0;
	            display: block;
	            background-color:#ff6f69;/*#ff6f69*/
		            
	            margin: 2%;
	            padding-bottom: 40px;
	            height: 150px;
	            background: radial-gradient(#ff6f69, #ff8b87);
	             background-size: 300%;
	            background-position:50% 50%;

            }
            .knockout {
	            
	            vertical-align: middle;
	            text-align: center;
	            font-family: 'Pacifico', cursive;
	            font-size:50pt;
	            color: blue;
	            color: #fff;
	            background: linear-gradient(110deg, #ffeead 33%, rgba(0, 0, 0, 0) 33%), linear-gradient(110deg, #C5E7D7 34%, #88d8b0 34%);
	            background-size: 400%;
	             -webkit-text-fill-color: transparent;
              -webkit-background-clip: text;
            }
            .knockout{
		            animation: Gradient 5s ease infinite;
		            -webkit-animation: Gradient 15s ease infinite;
		            -moz-animation: Gradient 5s ease infinite;
            }

            @-webkit-keyframes Gradient {
	            0% {
		            background-position: 30% 50%
	            }
	            50% {
		            background-position: 25% 50%
	            }
	            100% {
		            background-position: 30% 50%
	            }
	            
            }
            .main{
	            width: 500px;
	            height: 180px;
                position:relative;
                left: 500px;
            }
        /* Header */
        
        /*menu*/
            .menu {
              position: relative;
              top:100px;
              background: #cd3e3d;
              width: 5em;
              height: 5em;
              border-radius: 5em;
              margin: auto;
              margin-top: 5em;
              margin-bottom: 5em;
              cursor: pointer;
              border: 1em solid #fdaead;
            }
            .menu:after {
              content: "";
              position: absolute;
              top: 1em;
              left: 1em;
              width: 1em;
              height: 0.2em;
              border-top: 0.6em double #fff;
              border-bottom: 0.2em solid #fff;
            }
            .menu ul {
              list-style: none;
              padding: 0;
            }
            .menu li {
              width: 8em;
              height: 2.4em;
              padding: 0.2em;
              margin-top: 0.2em;
              text-align: center;
              border-top-right-radius: 0.5em;
              border-bottom-right-radius: 0.5em;
              transition: all 1s;
              background: #fdaead;
              opacity: 0;
              z-index: -1;
            }
            .menu:hover li {
              opacity: 1;
            }
            /**
             * Add a pseudo element to cover the space
             * between the links. This is so the menu
             * does not lose :hover focus and disappear
             */
            .menu:hover ul::before {
              position: absolute;
              content: "";
              width: 0;
              height: 0;
              display: block;
              left: 50%;
              top: -5.0em;
              /**
               * The pseudo-element is a semi-circle
               * created with CSS. Top, bottom, and right
               * borders are 6.5em (left being 0), and then
               * a border-radius is added to the two corners
               * on the right.
               */
              border-width: 6.5em;
              border-radius: 0 7.5em 7.5em 0;
              border-left: 0;
              border-style: solid;
              /**
               * Have to have a border color for the border
               * to be hoverable. I'm using a very light one
               * so that it looks invisible.
               */
              border-color: rgba(0,0,0,0.01);
              /**
               * Put the psuedo-element behind the links
               * (So they can be clicked on)
               */
              z-index: -1;
              /**
               * Make the cursor default so it looks like
               * nothing is there
               */
              cursor: default;
            }
            .menu a {
              color: white;
              text-decoration: none;
              /**
               * This is to vertically center the text on the
               * little tab-like things that the text is on.
               */
              line-height: 1.5em;
            }
            .menu a {
              color: white;
              text-decoration: none;
            }
            .menu ul {
              transform: rotate(180deg) translateY(-2em);
              transition: 1s all;
            }
            .menu:hover ul {
              transform: rotate(0deg) translateY(-1em);
            }
            .menu li:hover {
              background: #cd3e3d;
              z-index: 10;
            }

            .menu li:nth-of-type(1) {
              transform: rotate(-90deg);
              position: absolute;
              left: -1.2em;
              top: -7em;
            }
            .menu li:nth-of-type(2) {
              transform: rotate(-45deg);
              position: absolute;
              left: 5em;
              top: -4em;
            }
            .menu li:nth-of-type(3) {
              position: absolute;
              left: 7em;
              top: 1.5em;
            }
            .menu li:nth-of-type(4) {
              transform: rotate(45deg);
              position: absolute;
              left: 4.5em;
              top: 6.7em;
            }
            .menu li:nth-of-type(5) {
              transform: rotate(90deg);
              position: absolute;
              left: -1.2em;
              top: 9em;
            }
            .menu li:nth-of-type(6) {
              transform: rotate(135deg);
              position: absolute;
              left: -7em;
              top: 7em;
            }
            .hint {
              text-align: center;
            }
        /*menu*/

        </style>
            <div class ="jumbotron">
            <div class="main">
	            <div class = "border">
		            <div class = "inner-cutout"> 
			            <h1 class="knockout">MAIN MENU</h1>
		            </div>
	            </div>
            </div>
            </div>
            <div class="logout-button">
                <form action = "logout">
                    <button type = "Submit" class="btn btn-danger">
                    <span class="glyphicon glyphicon-log-out"></span> Log out</button>
                </form>
            </div>
            <c:choose>
                <c:when test = "${role == 'admin'}">
                    <nav class="menu">
                      <ul>
                        <li><a href="<%=request.getContextPath() %>/profile">Profile</a></li>
                        <li><a href="<%=request.getContextPath() %>/role">Role</a></li>
                        <li><a href="<%=request.getContextPath() %>/viewUsers">User</a></li>
                        <li><a href="<%=request.getContextPath() %>/viewProducts">Products</a></li>
                        <li><a href="<%=request.getContextPath() %>/viewCarts">Cart</a></li>
                        <li><a href="<%=request.getContextPath() %>/order">Order</a></li>
                      </ul>
                    </nav>
                </c:when>
                <c:otherwise>
                    <nav class="menu">
                      <ul>
                        <li><a href="<%=request.getContextPath() %>/profile">Profile</a></li>
                        <li><a href="<%=request.getContextPath() %>/viewUsers">User</a></li>
                        <li><a href="<%=request.getContextPath() %>/viewProducts">Products</a></li>
                        <li><a href="<%=request.getContextPath() %>/viewCarts">Cart</a></li>
                        <li><a href="<%=request.getContextPath() %>/order">Order</a></li>
                      </ul>
                    </nav>
                </c:otherwise>
            </c:choose>
    </body>
</html>
