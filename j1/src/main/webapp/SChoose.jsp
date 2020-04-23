
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <link rel="shortcut icon" href="assets/img/favicon.png">
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700&amp;subset=latin-ext" rel="stylesheet">
    <link rel="stylesheet" type="text/css" href="assets/css/plugins.css">
    <link rel="stylesheet" type="text/css" href="assets/css/main.css">
    <script type="text/javascript" src="js/modernizr.custom.86080.js"></script>

</head>
<body>

<div class="preloader">
    <!-- SPINNER -->
    <div class="spinner">
        <div class="bounce-1"></div>
        <div class="bounce-2"></div>
        <div class="bounce-3"></div>
    </div>
    <!-- /SPINNER -->
</div>

<div class="hero">
    <!-- FRONT CONTENT -->
    <div class="front-content">
        <!-- CONTAINER MID -->
        <div class="container-mid">
            <!-- ANIMATION CONTAINER -->
            <div class="animation-container animation-fade-down" data-animation-delay="0">
                <img class="img-responsive logo" src="assets/img/logo.png" alt="image">
            </div>
            <div class="animation-container animation-fade-right" data-animation-delay="300">
                <h1>Welcome to the Student platform</h1>
            </div>
            <div class="animation-container animation-fade-left" data-animation-delay="600">
                <p class="subline">Please choose your service!</p>
            </div>

            <form action="/j1_war_exploded/SSubmit.jsp" method=post >
                <div class="col-md-7 col-sm-7 col-xs-7">
                    <input name="sId" readonly  value="${pageContext.request.getParameter("sId")}"/>
                </div>
                <div class="animation-container animation-fade-up" data-animation-delay="900">
                    <div type="submit" >Submit Homework</div>
                </div>
            </form>


            <form action="/j1_war_exploded/main.jsp" method=post >
                <div class="animation-container animation-fade-up" data-animation-delay="900">
                    <!--  <div type="submit" class="open-popup">Student</div>-->
                    <div type="submit">Back</div>
                </div>
            </form>


        </div>
        <!-- /CONTAINER MID -->


        <!-- FOOTER -->
        <div class="footer">
            <!-- ANIMATION CONTAINER -->
            <div class="animation-container animation-fade-up" data-animation-delay="1200">
                <p>© 2020 Erin的作业管理系统 </p>
            </div>
            <!-- /ANIMATION CONTAINER -->
        </div>
        <!-- /FOOTER -->
    </div>
    <!-- /FRONT CONTENT -->
    <!-- BACKGROUND CONTENT -->
    <div class="background-content parallax-on">
        <div class="background-overlay"></div>
        <div class="background-img layer" data-depth="0.05"></div>
    </div>
    <!-- /BACKGROUND CONTENT -->
</div>

<script type="text/javascript" src="assets/js/plugins.js"></script>
<script type="text/javascript" src="assets/js/main.js"></script>






</body>

<script type="text/javascript" src="js/particles.js"></script>
<script type="text/javascript" src="js/app.js"></script>
</html>
