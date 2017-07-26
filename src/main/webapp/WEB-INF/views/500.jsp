<!DOCTYPE html>
<html>
<head>
    <title>Computer Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="${pageContext.request.getContextPath()}/css/bootstrap.min.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.getContextPath()}/css/font-awesome.css" rel="stylesheet" media="screen">
    <link href="${pageContext.request.getContextPath()}/css/main.css" rel="stylesheet" media="screen">
</head>
<body>
<header class="navbar navbar-inverse navbar-fixed-top">
    <c:import url="./header.jsp"></c:import>
</header>

<section id="main">
    <div class="container">
        <div class="alert alert-danger">
            Error 500: An error has occured!
            <br/>
            <!-- stacktrace -->
        </div>
    </div>
</section>

<script src="${pageContext.request.getContextPath()}/js/jquery.min.js"></script>
<script src="${pageContext.request.getContextPath()}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.getContextPath()}/js/dashboard.js"></script>

</body>
</html>