<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="pageDto" required="true" type="com.computer_database.dto.PageDto" %>

<%@ attribute name="search" required="true" type="java.lang.String" %>


<div class="container text-center">
    <ul class="pagination">
        <c:if test="${pageDto.pageCurrent-1  >= 0}">

            <li><a href="dashboard?page=0&limit=${pageDto.limit}&search=${search}" aria-label="First"> <span
                    aria-hidden="true">&larr;</span>
            </a></li>
        </c:if>

        <c:if test="${pageDto.pageCurrent-1  >= 0}">
            <li><a href="dashboard?page=${pageDto.pageCurrent-1}&limit=${pageDto.limit}&search=${search}"
                   aria-label="Previous"> <span
                    aria-hidden="true">&laquo;</span>
            </a></li>
        </c:if>

        <c:if test="${pageDto.pageCurrent-2  >= 0}">
            <li>
                <a href="dashboard?page=${pageDto.pageCurrent-2}&limit=${pageDto.limit}&search=${search}">${pageDto.pageCurrent-2+1}</a>
            </li>
        </c:if>

        <c:if test="${pageDto.pageCurrent-1  >= 0}">
            <li>
                <a href="dashboard?page=${pageDto.pageCurrent-1}&limit=${pageDto.limit}&search=${search}">${pageDto.pageCurrent-1+1}</a>
            </li>
        </c:if>

        <li class="active"><a href="dashboard?page=${pageDto.pageCurrent}&search=${search}">${pageDto.pageCurrent+1}</a>
        </li>

        <c:if test="${pageDto.pageCurrent+1  <= pageDto.pageTotal-1}">
            <li>
                <a href="dashboard?page=${pageDto.pageCurrent+1}&limit=${pageDto.limit}&search=${search}">${pageDto.pageCurrent+1+1}</a>
            </li>
        </c:if>

        <c:if test="${pageDto.pageCurrent+2  <= pageDto.pageTotal-1}">
            <li>
                <a href="dashboard?page=${pageDto.pageCurrent+2}&limit=${pageDto.limit}&search=${search}">${pageDto.pageCurrent+2+1}</a>
            </li>
        </c:if>

        <c:if test="${pageDto.pageCurrent+1  <= pageDto.pageTotal-1}">
            <li><a href="dashboard?page=${pageDto.pageCurrent+1}&limit=${pageDto.limit}&search=${search}"
                   aria-label="Next"> <span
                    aria-hidden="true">&raquo;</span>
            </a></li>
        </c:if>

        <c:if test="${pageDto.pageCurrent+1  <= pageDto.pageTotal-1}">
            <li><a href="dashboard?page=${pageDto.pageTotal-1}&limit=${pageDto.limit}&search=${search}"
                   aria-label="Last"> <span
                    aria-hidden="true">&rarr;</span>
            </a></li>
        </c:if>
    </ul>

    <div class="btn-group btn-group-sm pull-right" role="group">
        <c:choose>
            <c:when test="${ pageDto.limit == 10 }">
                <a href="dashboard?page=0&limit=10&search=${search}">
                    <button type="button" class="btn btn-primary">10</button>
                </a>
                <a href="dashboard?page=0&limit=50&search=${search}">
                    <button type="button" class="btn btn-default">50</button>
                </a>
                <a href="dashboard?page=0&limit=100&search=${search}">
                    <button type="button" class="btn btn-default">100</button>
                </a>
            </c:when>
            <c:when test="${ pageDto.limit == 50  }">
                <a href="dashboard?page=0&limit=10&search=${search}">
                    <button type="button" class="btn btn-default">10</button>
                </a>
                <a href="dashboard?page=0&limit=50&search=${search}">
                    <button type="button" class="btn btn-primary">50</button>
                </a>
                <a href="dashboard?page=0&limit=100&search=${search}">
                    <button type="button" class="btn btn-default">100</button>
                </a></c:when>
            <c:when test="${ pageDto.limit == 100  }">
                <a href="dashboard?page=0&limit=10&search=${search}">
                    <button type="button" class="btn btn-default">10</button>
                </a>
                <a href="dashboard?page=0&limit=50&search=${search}">
                    <button type="button" class="btn btn-default">50</button>
                </a>
                <a href="dashboard?page=0&limit=100&search=${search}">
                    <button type="button" class="btn btn-primary">100</button>
                </a>
            </c:when>
            <c:otherwise>
                <a href="dashboard?page=0&limit=10&search=${search}">
                    <button type="button" class="btn btn-default">10</button>
                </a>
                <a href="dashboard?page=0&limit=50&search=${search}">
                    <button type="button" class="btn btn-default">50</button>
                </a>
                <a href="dashboard?page=0&limit=100&search=${search}">
                    <button type="button" class="btn btn-default">100</button>
                </a></c:otherwise>
        </c:choose>

    </div>
</div>
