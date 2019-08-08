<!DOCTYPE html>
<html lang="en">
    <head>
      <title>Online shop</title>
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <style>

        h1 {
          text-shadow: 2px 2px #FF0000;
        }
        .carousel-control.right, .carousel-control.left {
          background-color: #ccffcc;
          color: #f4511e;
        }

        .carousel-indicators li {
          border-color: #f4511e;
        }

        .carousel-indicators li.active {
          background-color: #f4511e;
        }

        .item h4 {
          font-size: 19px;
          line-height: 1.375em;
          font-weight: 400;
          font-style: italic;
          margin: 70px 0;
        }

        .item span {
          font-style: normal;
        }
        .box {
	        display: flex;
        }

        .box .inner {
            position:relative;
            left: 150px;
	        width: 600px;
	        height: 200px;
	        line-height: 200px;
	        font-size: 4em;
	        font-family: sans-serif;
	        font-weight: bold;
	        white-space: nowrap;
	        overflow: hidden;
        }

        .box .inner:first-child {
	        background-color: indianred;
	        color: darkred;
	        transform-origin: right;
	        transform: perspective(100px) rotateY(-15deg);
        }

        .box .inner:last-child {
	        background-color: lightcoral;
	        color: antiquewhite;
	        transform-origin: left;
	        transform: perspective(100px) rotateY(15deg);
        }

        .box .inner span {
	        position: absolute;
	        animation: marquee 5s linear infinite;
        }

        .box .inner:first-child span {
	        animation-delay: 2.5s;
	        left: -100%;
        }

        @keyframes marquee {
	        from {
		        left: 100%;
	        }

	        to {
		        left: -100%;
	        }
        }
        .jumbotron {
           /* background-color: #66ff99;  Orange */
            background-image: url('https://images.pexels.com/photos/949587/pexels-photo-949587.jpeg?cs=srgb&dl=background-blur-blurred-949587.jpg&fm=jpg');
            color: #000000;
            background-size: 1600px;

        }

        .neon {
            position: relative;
            overflow: hidden;
            filter: brightness(200%);
        }

        .text {
            background-color: black;
            color: white;
            font-size: 50px;
            font-weight: bold;
            font-family: sans-serif;
            text-transform: uppercase;
            position: relative;
            left: 380px;
            user-select: none;
        }

        .text::before {
            content: attr(data-text);
            position: absolute;
            color: white;
            filter: blur(0.02em);
            mix-blend-mode: difference;
        }

        .gradient {
            position: absolute;
            background: linear-gradient(45deg, black, #ff9999, green, #ff9999, blue);
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            mix-blend-mode: multiply;
        }

        .spotlight {
            position: absolute;
            top: -100%;
            left: -100%;
            right: 0;
            bottom: 0;
            background: 
            radial-gradient(
                circle,
                white,
                transparent 25%
            ) center / 25% 25%,
            radial-gradient(
                circle,
                white,
                black 25%
            ) center / 12.5% 12.5%;
            animation: light 5s linear infinite;
            mix-blend-mode: color-dodge;
        }

        @keyframes light {
            to {
                transform: translate(50%, 50%);
            }
        }
        #stitch {
         /*stitching*/
         outline: 1px dashed #98abb9;
         outline-offset: -5px;
         
         background-color: #556068;
         height: 50px;
         width: 200px;
         margin: 100px auto;
         position:relative;
         left: -20px;
         top: 10px;
         
         /*shadow*/
         -webkit-box-shadow: 2px 2px 2px #000;
         -moz-box-shadow: 2px 2px 2px #000;
         box-shadow: 2px 2px 2px #000;
        }
        h2 {
         font-size: 20px;
         line-height: 50px;
         font-family: Helvetica, sans-serif;
         font-weight: bold;
         text-align: center;
        }

        div.login {
            position: relative;
            top: 200px;
        }
        </style>
        <div class ="jumbotron">
            <div class="box">
                <div class="inner">
                    <span>ONLINE SHOPPING</span>
                </div>
                <div class="inner">
                    <span>ONLINE SHOPPING</span>
                </div>
            </div>
            <p align = "center"><i>Think.Find.Buy</i></p>   
            <div id = "stitch">
                <h2 align ="center">Login here</h2>
            </div class ="login">
                <form action = "validateLogin" method ="post">
                    <div style="text-align: center">
                        <input type="text" placeholder="Username" required name = "username">
                        <input type="password" placeholder="Password" required name = "password">
                        <button type="submit" class="btn btn-info">
                        <span class="glyphicon glyphicon-log-in"></span> Log in
                        </button>
                    </div>
                </form>
            </div>
            <div class="neon">
              <span class="text" data-text="WHAT OUR CUSTOMERS SAY">WHAT OUR CUSTOMERS SAY</span>
              <span class="gradient"></span>
              <span class="spotlight"></span>
            </div>
        <div id="myCarousel" class="carousel slide text-center" data-ride="carousel">
        <!-- Indicators -->
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>
            <!-- Wrapper for slides -->
            <div class="carousel-inner" role="listbox">
                <div class="item active">
                    <h4>"The Best!"<br><span style="font-style:normal;">Ashok, Mumbai</span></h4>
                </div>
                <div class="item">
                    <h4>"One word... WOW!!"<br><span style="font-style:normal;">Jon, Panaji</span></h4>
                </div>
                <div class="item">
                    <h4>"Value for money"<br><span style="font-style:normal;">Piyush, Chennai, </span></h4>
                </div>
            </div>
            <!-- Left and right controls -->
            <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
    </body>
</html>
