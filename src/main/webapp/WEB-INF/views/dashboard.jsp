<html>
<head>
    <title>Computer Database</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta charset="utf-8">
    <!-- Bootstrap -->
    <link href="${pageContext.request.getContextPath()}/css/bootstrap.css" rel="stylesheet" media="screen">
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
        <h1 id="homeTitle">${computerCount} Computers found</h1>
        <div id="actions" class="form-horizontal">
            <div class="pull-left">
                <form id="searchForm" action="dashboard" method="GET" class="form-inline">

                    <input type="search" id="searchbox" name="search"
                           class="form-control" placeholder="Search name"
                           value="${search}"/> <input
                        type="submit" id="searchsubmit" value="Filter by name"
                        class="btn btn-primary"/>
                </form>
            </div>
            <div class="pull-right">
                <a class="btn btn-success" id="addComputer" href="manageComputer?id=0">Add
                    Computer</a> <a class="btn btn-default" id="editComputer" href="#"
                                    onclick="$.fn.toggleEditMode();">Edit</a>
            </div>
        </div>
    </div>

    <form id="deleteForm" action="dashboard" method="POST">
        <input type="hidden" name="selection" value="">
    </form>

    <div class="container" style="margin-top: 10px;">
        <table class="table table-striped table-bordered">
            <thead>
            <tr>
                <!-- Variable declarations for passing labels as parameters -->
                <!-- Table header for Computer Name -->

                <th class="editMode" style="width: 60px; height: 22px; display: none;">
                    <input type="checkbox" id="selectall"/>
                    <span style="vertical-align: top;"> -
                        <a href="#" id="deleteSelected" onclick="$.fn.deleteSelected();">
                            <i class="fa fa-trash-o fa-lg"></i>
						</a>
						</span></th>
                <form id="myThLinkForm">
                    <th style="width: 23%;">
                        <a id="myLinkComputer"
                           href="dashboard?page=${pageDto.pageCurrent}&limit=${pageDto.limit}&search=${search}&order=computer.name">Computer
                            name</a></th>
                    <th style="width: 23%;">
                        <a id="myLinkIntroduced"
                           href="dashboard?page=${pageDto.pageCurrent}&limit=${pageDto.limit}&search=${search}&order=computer.introduced">Introduced
                            date</a></th>
                    <th style="width: 23%;">
                        <a id="myLinkDiscontinued"
                           href="dashboard?page=${pageDto.pageCurrent}&limit=${pageDto.limit}&search=${search}&order=computer.discontinued">Discontinued
                            date</a></th>
                    <th style="width: 23%;">
                        <a id="myLinkCompany"
                           href="dashboard?page=${pageDto.pageCurrent}&limit=${pageDto.limit}&search=${search}&order=computer.company_id">Company</a>
                    </th>
                </form>

            </tr>
            </thead>
            <!-- Browse attribute computers -->
            <tbody id="results">
            <c:forEach items="${pageDto.datas}" var="data">
                <tr>
                    <td class="editMode"><input type="checkbox" name="cb"
                                                class="cb" value="${data.id}"></td>
                    <td><a href="manageComputer?id=${data.id}" onclick="">${data.name}</a></td>
                    <td>${data.introduced}</td>
                    <td>${data.discontinued}</td>
                    <td>${data.company.name}</td>

                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</section>

<footer class="navbar-fixed-bottom">
    <myTag:paging pageDto="${pageDto}" search="${search}"/>
</footer>

<script src="${pageContext.request.getContextPath()}/js/jquery.min.js"></script>
<script src="${pageContext.request.getContextPath()}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.getContextPath()}/js/dashboard.js"></script>

</body>
</html>