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
<!-- body header -->
<header class="navbar navbar-inverse navbar-fixed-top">
    <c:import url="./header.jsp"></c:import>
</header>
<section id="main">
    <div class="container">
        <div class="row">
            <div class="col-xs-8 col-xs-offset-2 box">
                <h1>Add Computer</h1>
                <form id="addComputer" action="manageComputer?id=0" method="POST">
                    <fieldset>
                        <div class="form-group">
                            <label for="computerName">Computer name</label>
                            <input type="text" class="form-control" id="computerName" name="computerName"
                                   placeholder="Computer name" required>
                            <div id="messageErrorName">${form.errors['name']}</div>
                        </div>
                        <div class="form-group">
                            <label for="introduced">Introduced date</label>
                            <input type="date" class="form-control" id="introduced" name="computerIntroduced"
                                   placeholder="Introduced date">
                            <div id="messageErrorIntroduced">${form.errors['introduced']}</div>
                        </div>
                        <div class="form-group">
                            <label for="discontinued">Discontinued date</label>
                            <input type="date" class="form-control" id="discontinued" name="computerDiscontinued"
                                   placeholder="Discontinued date">
                            <div id="messageErrorDiscontinued">${form.errors['discontinued']}</div>
                        </div>
                        <div class="form-group">
                            <label for="companyId">Company</label>
                            <select class="form-control" id="companyId" name="computerCompanyId">
                                <option value="0">--</option>
                                <c:forEach items="${companies}" var="company">
                                    <option value="${company.id}">${company.name}</option>
                                </c:forEach>
                            </select>
                            <div id="messageErrorCompanyId">${form.errors['companyId']}</div>
                        </div>
                    </fieldset>

                    <div class="actions pull-right">
                        <input type="submit" value="Add" class="btn btn-primary">
                        or
                        <a href="." class="btn btn-default">Cancel</a>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>

<script src="${pageContext.request.getContextPath()}/js/jquery.min.js"></script>
<script src="${pageContext.request.getContextPath()}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.getContextPath()}/js/manageComputer.js"></script>

</body>
</html>