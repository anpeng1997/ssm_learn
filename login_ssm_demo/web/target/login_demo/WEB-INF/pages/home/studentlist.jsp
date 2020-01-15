<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <title>list</title>
    <link rel="stylesheet" href="/static/css/bootstrap.min.css"/>
</head>
<body>
<div class="container" style="margin-top: 50px;">
    <a class="btn btn-light" href="/home/addStudent">add student</a>
    <button class="btn btn-danger" id="deleteBtn">删除选中</button>
    <form id="studentForm" action="/home/deleteStudents" method="post">
        <table class="table">
            <thead class="thead-light">
            <tr>
                <th><input type="checkbox"></th>
                <th scope="col">name</th>
                <th scope="col">age</th>
                <th scope="col">score</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${pageData.students}" varStatus="v" var="student">
                <tr>
                    <th><input type="checkbox" name="ids" value="${student.id}"></th>
                    <td>${student.name}</td>
                    <td>${student.age}</td>
                    <td>${student.score}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </form>
    <nav aria-label="...">
        <ul class="pagination">
            <li class="page-item ${pageData.currentPage == 1 ? "disabled":""}">
                <a class="page-link"
                   href="/home/studentList?pageNumber=${pageData.currentPage-1}&pageSize=5"
                   tabindex="-1" aria-disabled="true">
                    <span aria-hidden="true">&laquo;</span>
                </a>
            </li>
            <c:forEach step="1" begin="1" var="i" end="${pageData.totalPage}">
                <li class="page-item ${i == pageData.currentPage ? "active":""}" aria-current="page">
                    <c:if test="${i != pageData.currentPage}">
                        <a class="page-link"
                           href="/home/studentList?pageNumber=${i}&pageSize=5">
                                ${i}
                        </a>
                    </c:if>
                    <c:if test="${i == pageData.currentPage}">
                         <span class="page-link">
                                 ${i}
                         </span>
                    </c:if>
                </li>
            </c:forEach>
            <li class="page-item ${pageData.currentPage == pageData.totalPage ? "disabled":""}">
                <a class="page-link"
                   href="/home/studentList?pageNumber=${pageData.currentPage+1}&pageSize=5">
                    <span aria-hidden="true">&raquo;</span>
                </a>
            </li>
        </ul>
    </nav>
    <h3>总共${pageData.totalPage}条页，共${pageData.totalCount}记录</h3>
</div>
<script>
    window.onload = function () {
        document.getElementById("deleteBtn").onclick = function () {
            document.getElementById("studentForm").submit();
        }
    }
</script>
</body>
</html>
